package com.allen.sys.aop;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.allen.sys.annotation.MethodLog;
import com.allen.sys.model.po.SysOperationLog;
import com.allen.sys.model.po.SysUser;
import com.allen.sys.service.SysOperationLogService;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xuguocai 2020/5/29 9:44
 */

@Aspect
@Component
public class OperateLogAspect {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysOperationLogService sysOperationLogService;

    @After(value = "execution (* com.allen.sys.controller..*.*(..))") //指定切点,使用after 让该切面在方法执行完成后切入
    public void after(JoinPoint point) throws Throwable {
        try {
            // 拦截的action方法上面的标记是否包含 MethodLog 的注解
            Map<String, Object> map = getMthodRemark(point);
            if (map.isEmpty()) {
                // 没有MethodLog 注解标记 ,无此配置,直接返回
                return;
            }
            //获取requestBody 参数信息,过滤掉 ServletRequest 和 ServletResponse 类型的参数
            Object object = Arrays.stream(point.getArgs()).filter(t ->!( t instanceof ServletRequest) && !( t instanceof ServletResponse) ).collect(Collectors.toList());
            //请求参数转成jsonString
            String requestBody = JSONObject.toJSONString(object);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (null == request) {
                log.info("获取request失败.直接返回");
                return;
            }
            //根据缓存获取当前用户信息
            SysUser loginUserDetails = new SysUser();
            loginUserDetails.setId(1);
            loginUserDetails.setLoginName("sysadmin");
            loginUserDetails.setName("admin");
            if (loginUserDetails == null) {
                log.error("未从缓存中获取到员工信息,直接返回");
                return;
            }
            //获取用户数使用客户端信息
            String userAgent = request.getHeader("user-agent");
            // action方法名称
            String actionName = point.getSignature().getName();
            // 构建操作日志对象
            SysOperationLog operationLog = new SysOperationLog();
            operationLog.setUserId(loginUserDetails.getId().toString());
            operationLog.setUserName(loginUserDetails.getName());

            operationLog.setOperContent(null == map.get("content") ? "" : map.get("content").toString());
            operationLog.setOperLevel(null == map.get("operLevel") ? "0" : map.get("operLevel").toString());
            operationLog.setOperParams(requestBody.substring(1, requestBody.length()-1));
            operationLog.setOperUserAgent(userAgent);
            operationLog.setOperMethod(actionName);
            operationLog.setOperIp(getIpAdrress(request));
            operationLog.setOperType(null == map.get("operType") ? "0" : map.get("operType").toString());
            operationLog.setCreateTime(new Date());
            sysOperationLogService.insert(operationLog);
        }catch (JSONException je){
            log.error("记录操作日志参数异常,json转型异常：{}", je.getMessage());
        } catch (Exception e) {
            log.error("记录操作日志出错：{}", e.getMessage());
        }
        return;
    }

    /**
     * 获取方法的中文备注____用于记录用户的操作日志描述
     * @param joinPoint
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> getMthodRemark(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Map<String, Object> map = Maps.newHashMap();
        Class targetClass = Class.forName(targetName);
        Method[] method = targetClass.getMethods();
        String methode = "";
        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                Class[] tmpCs = m.getParameterTypes();
                if (tmpCs.length == arguments.length) {
                    MethodLog methodCache = m.getAnnotation(MethodLog.class);
                    if (methodCache != null) {
                        methode = methodCache.content();
                        String operType = methodCache.operType();
                        int level = methodCache.operLevel();
                        map.put("content", methode);
                        map.put("operLevel", level);
                        map.put("operType", operType);
                    }
                    break;
                }
            }
        }
        return map;
    }

    /**
     * 获取IP地址
     * @param request
     * @return
     */
    private static String getIpAdrress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(!StringUtils.isEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(!StringUtils.isEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }

    public static void main(String[] args) {
        String msg ="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36";
        System.out.println("msg:{}"+msg.length());
    }

}
