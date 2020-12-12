package com.allen.sys.controller;

import com.allen.sys.common.ResponseBean;
import com.allen.sys.common.ResponseBeanUtil;
import com.allen.sys.constants.ResponseCodeEnum;
import com.allen.sys.mapper.SysUserLoginMapper;
import com.allen.sys.model.dto.LoginForm;
import com.allen.sys.model.dto.UserParam;
import com.allen.sys.model.dto.UserPwdForm;
import com.allen.sys.model.dto.UserRoleForm;
import com.allen.sys.model.po.SysRole;
import com.allen.sys.model.po.SysUser;
import com.allen.sys.model.po.SysUserLogin;
import com.allen.sys.model.vo.UserLoginVo;
import com.allen.sys.service.SystemService;
import com.allen.sys.utils.AddressIpUtil;
import com.allen.sys.utils.TokenUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;


/**
 * @author: allen小哥 2020-04-14 11:18
 **/
@RestController
@CrossOrigin
@RequestMapping("/admin/auth")
public class AuthController {

	private Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private SystemService systemService;
    @Autowired
    SysUserLoginMapper userLoginMapper;

	/**
	 * create token
	 *
	 * @param loginForm
	 * @return
	 */
	@PostMapping("/ssoLogin")
	public ResponseBean ssoLogin(HttpServletRequest request, @RequestBody LoginForm loginForm) {
		LOGGER.info("loginName:{},password:{}" , loginForm.getLoginName() ,loginForm.getPassword());
		SysUser sysUser = systemService.ssoLogin(loginForm);
		if (sysUser == null){
			return ResponseBeanUtil.fail(ResponseCodeEnum.ACCOUNT_PASSWORD);
		}
		String token = TokenUtils.token(sysUser.getName(),sysUser.getPassword());
		UserLoginVo loginVo = new UserLoginVo();
		loginVo.setLoginName(sysUser.getLoginName());
		loginVo.setUserName(sysUser.getName());
		loginVo.setToken(token);

        SysUserLogin userLogin = new SysUserLogin();
		userLogin.setUserId(sysUser.getId());
        userLogin.setUserName(sysUser.getName());
        userLogin.setLoginName(sysUser.getLoginName());
        userLogin.setToken(token);
        userLogin.setLoginIp(AddressIpUtil.getIpAdrress(request));
        userLogin.setCreateTime(new Date());
        userLogin.setLoginTime(new Date());
        userLoginMapper.insertSelective(userLogin);
		return ResponseBeanUtil.ok(loginVo);
	}

	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@GetMapping("/logout")
	public ResponseBean deleteToken(HttpServletRequest request){
		String tokenHeader = request.getHeader("Authorization");
		System.out.println(tokenHeader);
		SysUserLogin userLogin = new SysUserLogin();
		userLogin.setToken(tokenHeader);
		userLoginMapper.delete(userLogin);
		LOGGER.info("====================退出成功=========================");
		return ResponseBeanUtil.ok();
	}

    /**
     * Save current user info response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @PostMapping(value = "/user/info")
    public ResponseBean saveCurrentUserInfo(@Valid @RequestBody SysUser user) {
		SysUser authUser = new SysUser();
        //只能更新当前用户信息
        if (authUser.getId().equals(user.getId())) {
            // 保存用户信息
            systemService.updateUserInfo(user);
        }
        return ResponseBeanUtil.ok();
    }
    
    /**
     * Reset password response entity.
     *
     * @param userInfoDto
     * @return the response entity
     */
    @PostMapping(value = "/user/password")
    public ResponseBean updatePassword(@RequestBody UserPwdForm userInfoDto) {
        String oldPassword = userInfoDto.getOldPassword();
        String newPassword = userInfoDto.getNewPassword();

        try {
			SysUser user = new SysUser();
			// 重置密码
//			if (StrUtil.isNotBlank(oldPassword) && StrUtil.isNotBlank(newPassword)) {
//			    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
//			        return ResponseBeanUtil.fail(-1,  "旧密码错误");
//			    }
//			    systemService.updateUserPasswordById(user.getId(), passwordEncoder.encode(newPassword));
//			}
			return ResponseBeanUtil.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBeanUtil.fail(ResponseCodeEnum.FAIL);
		}
    }
    
    /**
     * List page info.
     *
     * @param param
     * @return the page info
     */
    @PostMapping(value = "/user/list")
    public ResponseBean userList(@RequestBody UserParam param) {
		PageInfo<SysUser> userPage = systemService.findUserPage(param);
		return ResponseBeanUtil.ok(userPage);
    }

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return the user
     */
    @GetMapping(value = "/user/getUserInfo")
    public ResponseBean getUserInfo(Integer userId) {
		SysUser user = systemService.getUserById(userId);
		return ResponseBeanUtil.ok(user);
    }

    /**
     * Save user sys user.
     *
     * @param user
     * @return the sys user
     */
    @PostMapping(value = "/user/edit")
    public ResponseBean saveUser( @RequestBody SysUser user) {
    	if (null == user){
    		return ResponseBeanUtil.fail(ResponseCodeEnum.FAIL);
		}
    	try {
			String password = user.getPassword();
			//如果是新增用户
			if( user.getId() == null && !StringUtils.isEmpty(password)){
				//用户密码不能为空
				user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			}
			// 保存用户信息
			systemService.saveUser(user);
	        return ResponseBeanUtil.ok();
		} catch (Exception e) {
			e.printStackTrace();
	        return ResponseBeanUtil.fail(ResponseCodeEnum.FAIL);
		}
    }
    
    /**
     * 用户信息更新密码字段
     * <p>Title: editPassword</p>  
     * <p>Description: </p>  
     * @param pwdForm
     * @return
     */
    @PostMapping(value = "/user/editPassword")
    public ResponseBean editPassword( @RequestBody UserPwdForm pwdForm) {
    	try {
			SysUser user = new SysUser();
			Integer userId = pwdForm.getId();
			String newPassword = pwdForm.getNewPassword();
			user.setId(userId);
			user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
			// 保存用户信息
			systemService.saveUser(user);
	        return ResponseBeanUtil.ok();
		} catch (Exception e) {
			e.printStackTrace();
	        return ResponseBeanUtil.fail(ResponseCodeEnum.FAIL);
		}
    }

    /**
     * Delete response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @GetMapping(value = "/user/deleteUser")
    public ResponseBean userDelete(Integer userId) {
        systemService.deleteUserById(userId);
        return ResponseBeanUtil.ok();
    }
    
    /**
     * 获取用户已授权列表
     * @param userId
     * @return
     */
	@GetMapping(value = "/user/getUserRoleByUserId")
	public ResponseBean getUserRoleByUserId(Integer userId) {
		if (null == userId){
			return ResponseBeanUtil.fail(ResponseCodeEnum.FAIL);
		}

		try {
			List<SysRole> authList = systemService.getUserRoleByUserId(userId);
			return ResponseBeanUtil.ok(authList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBeanUtil.fail(ResponseCodeEnum.FAIL);
		}
	}

    /**
     * 保存用户与角色权限关系
     * @param userRoleForm
     * @return
     */
    @PostMapping(value = "/user/saveUserRole")
    public ResponseBean saveUserRole( @RequestBody UserRoleForm userRoleForm) {
    	try {
			systemService.saveUserRole(userRoleForm);
	        return ResponseBeanUtil.ok("授权成功");
		} catch (Exception e) {
			e.printStackTrace();
	        return ResponseBeanUtil.fail(ResponseCodeEnum.FAIL);
		}
    }
    
    /**
     * <p>Title: getUserByLoginName</p>  
     * <p>Description: </p>  
     * @param loginName
     * @return
     */
    @GetMapping(value = "/user/getUserByLoginName")
    public ResponseBean getUserByLoginName(String loginName) {
    	SysUser user = systemService.getUserByLoginName(loginName);
    	return ResponseBeanUtil.ok(user);
    }

}
