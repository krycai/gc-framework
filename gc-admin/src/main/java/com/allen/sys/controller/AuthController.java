package com.allen.sys.controller;

import cn.com.bluemoon.common.web.controller.BaseController;
import cn.com.bluemoon.common.web.security.JwtTokenUtil;
import cn.com.bluemoon.common.web.security.WebUtils;
import cn.com.bluemoon.qy.pojo.dto.*;
import cn.com.bluemoon.qy.pojo.po.SysRole;
import cn.com.bluemoon.qy.pojo.po.SysUser;
import cn.com.bluemoon.qy.pojo.vo.LoginUserInfo;
import cn.com.bluemoon.qy.service.IDubboUserService;
import cn.com.bluemoon.qy.service.SystemService;
import cn.com.bluemoon.qy.utils.AuthUserFactory;
import cn.com.bluemoon.redis.repository.RedisRepository;
import cn.com.bluemoon.utils.string.StringHelper;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.allen.sys.result.ResponseBean;
import com.allen.sys.result.ResponseBeanUtil;
import com.allen.sys.result.ResponseCodeEnum;
import com.allen.sys.service.SystemService;
import com.bluemoon.pf.standard.bean.ResponseBean;
import com.bluemoon.pf.standard.constants.ResponseCodeEnum;
import com.bluemoon.pf.standard.utils.ResponseBeanUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * @author: allen小哥 2020-04-14 11:18
 **/
@RestController
@CrossOrigin
@RequestMapping("/admin/auth")
public class AuthController{
	
	private Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private SystemService systemService;
	@Autowired
	private RedisRepository redisRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;

	/**
	 * create token
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/ssoLogin")
	public ResponseBean ssoLogin(HttpServletRequest request, @RequestBody JSONObject jsonObject){
		String userName = jsonObject.getString("userName").trim();
		String password = jsonObject.getString("password").trim();
		LOGGER.info("=====ssoLoginLog==="+userName+"----------"+password);

		HttpSession session = request.getSession();
		session.setAttribute("userName",userName);
		//做用户登录次数限制，1分钟内不能超过5次
		Map<String, Object> map = systemService.userLoginLimit(userName);
		if( !(Boolean)map.get("isSuccess")){
			return ResponseBeanUtil.createFailBean(ResponseCodeEnum.FAIL);
		}
		try {

			net.sf.json.JSONObject responseBean = new net.sf.json.JSONObject();
			if (!StrUtil.isEmpty(userName) && !StrUtil.isEmpty(password)) {
				responseBean = this.dubboUserService.userLogin(userName, password);
			} else {
				responseBean.put("responseMsg", "用户名、密码不能为空");
			}

			String token = "";
			//登陆成功
			if (responseBean.getBoolean("isSuccess") && null != responseBean.getJSONObject("user")) {
				net.sf.json.JSONObject userJson = responseBean.getJSONObject("user");
				AuthUser authUser = new AuthUser(userJson.getInt("account"));
				LoginUserInfo userInfo = new LoginUserInfo();

				String account = userJson.getString("account");
				//sso登陆，获取用户的菜单权限信息，然后组装userDetails
				//user对象必须赋上username等信息，否则getSubject返回kong
				SysUser user = this.systemService.getSsoLoginUserByLoginAccount(account);
				Assert.isTrue(null != user, "无此用户信息，请重新登陆");
				user.setId(Integer.parseInt(account));
				String realName = userJson.optString("realName", "");
				user.setLoginName(account);
				user.setName(realName);
				String mobile = userJson.optString("mobile", "");
				user.setMobile(mobile);
				authUser = AuthUserFactory.create(user);
				token = jwtTokenUtil.generateToken(authUser, jwtTokenUtil.getRandomKey());
				session.setAttribute("user",authUser);
				String jwtToken = JwtTokenUtil.TOKEN_TYPE_BEARER + " " + token;

				userInfo.setAuthorization(jwtToken);
				userInfo.setAuthUser(authUser);
				return ResponseBeanUtil.createScBean(ResponseCodeEnum.SC.getDoc(),userInfo);
			} else {
				LOGGER.info("ssoLogin登录失败，具体信息：{}", responseBean.getString("responseMsg"));
				return ResponseBeanUtil.createFailBean(responseBean.getInt("responseCode"), responseBean.getString("responseMsg"));
			}

		} catch (UsernameNotFoundException e) {
			LOGGER.info("用户认证失败：" + "userName wasn't in the system");
			return ResponseBeanUtil.createFailBean(ResponseCodeEnum.ACCOUNT_PASSWORD);
		} catch (LockedException lae) {
			LOGGER.info("用户认证失败：" + "account for that username is locked, can't login");
			return ResponseBeanUtil.createFailBean(1005,"账号已被锁定，不能登录");
		} catch (AuthenticationException ace) {
			LOGGER.info("用户认证失败：" + ace);
			ace.printStackTrace();
			return ResponseBeanUtil.createFailBean(ResponseCodeEnum.ACCOUNT_ABNORMAL);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBeanUtil.createFailBean(ResponseCodeEnum.ACCOUNT_ABNORMAL);
		}
	}

	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@GetMapping("/logout")
	public ResponseBean deleteToken(HttpServletRequest request){
		String tokenHeader = request.getHeader("Authorization");
		String token = tokenHeader.split(" ")[1];
		jwtTokenUtil.deleteToken(token);
		LOGGER.info("====================退出成功=========================");
		return ResponseBeanUtil.createScBean();
	}

    /**
     * Gets current user info.
     *
     * @return the current user info
     */
    @GetMapping(value = "/user/info")
    public ResponseBean getCurrentUserInfo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseBeanUtil.createScBean(principal);
    }

    /**
     * Save current user info response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @PostMapping(value = "/user/info")
    public ResponseBean saveCurrentUserInfo(@Valid @RequestBody SysUser user) {
        AuthUser authUser = WebUtils.getCurrentUser();
        //只能更新当前用户信息
        if (authUser.getId().equals(user.getId())) {
            // 保存用户信息
            systemService.updateUserInfo(user);
        }
        return ResponseBeanUtil.createScBean();
    }
    
    /**
     * Reset password response entity.
     *
     * @param userInfoDto
     * @return the response entity
     */
    @PostMapping(value = "/user/password")
    public ResponseBean updatePassword(@RequestBody SysUserInfoDto userInfoDto) {
        String oldPassword = userInfoDto.getOldPassword();
        String newPassword = userInfoDto.getNewPassword();

        try {
			AuthUser user = WebUtils.getCurrentUser();
			// 重置密码
			if (StringHelper.isNotBlank(oldPassword) && StringHelper.isNotBlank(newPassword)) {
			    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
			        return ResponseBeanUtil.createFailBean(-1,  "旧密码错误");
			    }
			    systemService.updateUserPasswordById(user.getId(), passwordEncoder.encode(newPassword));
			}
			return ResponseBeanUtil.createScBean();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBeanUtil.createFailBean(ResponseCodeEnum.FAIL);
		}
    }
    
    /**
     * List page info.
     *
     * @param sysUserPageDto
     * @return the page info
     */
    @PostMapping(value = "/user/list")
    public ResponseBean userList(@RequestBody SysUserPageDto sysUserPageDto) {
		PageInfo<SysUser> userPage = systemService.findUserPage(sysUserPageDto.getPage(), sysUserPageDto.getUser());
		return ResponseBeanUtil.createScBean(userPage);
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
		return ResponseBeanUtil.createScBean(user);
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
    		return ResponseBeanUtil.createFailBean(ResponseCodeEnum.FAIL);
		}
    	try {
			String password = user.getPassword();
			//如果是新增用户
			if( user.getId() == null && !StringUtils.isEmpty(password)){
				//用户密码不能为空
				user.setPassword(passwordEncoder.encode(password));
			}
			// 保存用户信息
			systemService.saveUser(user);
	        return ResponseBeanUtil.createScBean();
		} catch (Exception e) {
			e.printStackTrace();
	        return ResponseBeanUtil.createFailBean(ResponseCodeEnum.FAIL);
		}
    }
    
    /**
     * 用户信息更新密码字段
     * <p>Title: editPassword</p>  
     * <p>Description: </p>  
     * @param userInfoDto
     * @return
     */
    @PostMapping(value = "/user/editPassword")
    public ResponseBean editPassword( @RequestBody SysUserInfoDto userInfoDto) {
    	try {
			SysUser user = new SysUser();
			int userId = userInfoDto.getId();
			String newPassword = userInfoDto.getNewPassword();
			user.setId(userId);
			user.setPassword(passwordEncoder.encode(newPassword));
			// 保存用户信息
			systemService.saveUser(user);
	        return ResponseBeanUtil.createScBean();
		} catch (Exception e) {
			e.printStackTrace();
	        return ResponseBeanUtil.createFailBean(ResponseCodeEnum.FAIL);
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
        return ResponseBeanUtil.createScBean();
    }
    
    /**
     * 将用户选择的皮肤信息存入缓存
     * @param skins
     * @return
     */
    @PostMapping(value = "/user/changeSkins")
    public ResponseBean changeSkins(@RequestBody String skins ){
    	AuthUser authUser = WebUtils.getCurrentUser();
    	Integer userId = authUser.getId();
    	if( StringHelper.isEmpty(skins) ){
    		return ResponseBeanUtil.createFailBean(1002,"请选择皮肤风格");
    	}else{
    		String redisKey = "style_skins_" + userId;
    		redisRepository.set(redisKey, skins);
    	}
    	return ResponseBeanUtil.createScBean();
    }
    
    @PostMapping(value = "/user/getSkins")
    public ResponseBean getSkins() {
    	AuthUser authUser = WebUtils.getCurrentUser();
    	Integer userId = authUser.getId();
		String redisKey = "style_skins_" + userId;
		String skins = redisRepository.get(redisKey);
    	return ResponseBeanUtil.createScBean(skins);
    }
    
    /**
     * 获取用户已授权列表
     * @param userId
     * @return
     */
	@GetMapping(value = "/user/getUserRoleByUserId")
	public ResponseBean getUserRoleByUserId(Integer userId) {
		if (null == userId){
			return ResponseBeanUtil.createFailBean(ResponseCodeEnum.FAIL);
		}

		try {
			List<SysRole> authList = systemService.getUserRoleByUserId(userId);
			return ResponseBeanUtil.createScBean(authList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBeanUtil.createFailBean(ResponseCodeEnum.FAIL);
		}
	}

    /**
     * 保存用户与角色权限关系
     * @param userRoleDto
     * @return
     */
    @PostMapping(value = "/user/saveUserRole")
    public ResponseBean saveUserRole( @RequestBody SysUserRoleDto userRoleDto) {
    	try {
			systemService.saveUserRole(userRoleDto);
	        return ResponseBeanUtil.createScBean("授权成功");
		} catch (Exception e) {
			e.printStackTrace();
	        return ResponseBeanUtil.createFailBean(ResponseCodeEnum.FAIL);
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
    	return ResponseBeanUtil.createScBean(user);
    }

}
