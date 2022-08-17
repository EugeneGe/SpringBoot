//package com.gly.springboot.config.aspect;
//
//import com.alibaba.fastjson.JSONObject;
//import com.gly.springboot.utils.DateUtil;
//import com.gly.springboot.utils.LogUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * AOP 接口调用日志
// *
// * @author EugeneGe
// * @date 2021-05-25 17:34
// */
//@Aspect
//@Component
//public class LogAspect {
//
//    private Logger logger = LoggerFactory.getLogger(LogAspect.class);
//
//    @Autowired
//    private ISysLogService iSysLogService;
//
//    public LogAspect() {
//    }
//
//    @Pointcut("execution(public * com.*.*.web.*.*(..)) || execution(public * com.*.*.controller.*.*(..)) || execution(public * com.*.web.*.*(..))")
//    public void webLog() {
//    }
//
//    @Around("webLog()")
//    public Object arround(ProceedingJoinPoint pjp) throws Throwable {
//        Object o = null;
//        long start = System.currentTimeMillis();
//        SysLog sysLog = new SysLog();
//        long end = 0L;
//
//        try {
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            HttpServletRequest request = attributes.getRequest();
//            String ip = LogUtils.getCliectIp(request);
//            if (!StringUtils.isEmpty(ip)) {
//                sysLog.setIp(ip);
//            }
//
//            sysLog.setUrl(request.getRequestURL().toString());
//            sysLog.setMethod(request.getMethod());
//            String Controller = pjp.getSignature().getDeclaringTypeName();
//            sysLog.setController(Controller);
//            sysLog.setAccessMethod(pjp.getSignature().getName());
//            sysLog.setParameter(Arrays.toString(pjp.getArgs()).length() > 4000 ? Arrays.toString(pjp.getArgs()).
//                    substring(0, 3999) : Arrays.toString(pjp.getArgs()));
//            Map<String, String> map = new HashMap();
//            Enumeration headerNames = request.getHeaderNames();
//
//            String resStr;
//            while (headerNames.hasMoreElements()) {
//                resStr = (String) headerNames.nextElement();
//                String value = request.getHeader(resStr);
//                map.put(resStr, value);
//            }
//
//            sysLog.setHeaders(map.toString());
//            sysLog.setRequestTime(DateUtil.dateToStr());
//
//            try {
//                o = pjp.proceed();
////                if (this.UserInfo != null) {
////                    resStr = "";
////
////                    try {
////                        resStr = this.UserInfo.getLoginName();
////                    } catch (Exception var19) {
////                        resStr = "";
////                    }
////
////                    sysLog.setLoginAccount(resStr);
////                } else {
////                    sysLog.setLoginAccount("null");
////                }
//
//                end = System.currentTimeMillis();
//                sysLog.setExecutionDuration((int) (end - start));
//                sysLog.setResponseTime(DateUtil.dateToStr());
//                sysLog.setCacheStatus(0);
//                resStr = "";
//                if (o != null) {
//                    resStr = JSONObject.toJSONString(o);
//                    if (resStr.length() > 10000) {
//                        resStr = resStr.substring(0, 9999);
//                    }
//
//                    sysLog.setResponse(resStr);
//                    sysLog.setResponseLength(resStr.length());
//                }
//
//                try {
//                    this.iSysLogService.save(sysLog);
//                } catch (Exception var18) {
//                    this.logger.error(var18.getMessage());
//                }
//
//                return o;
//            } catch (Throwable var20) {
//                var20.printStackTrace();
//                end = System.currentTimeMillis();
//                sysLog.setExecutionDuration((int) (end - start));
//                sysLog.setResponseTime(DateUtil.dateToStr());
//                sysLog.setCacheStatus(0);
//                sysLog.setResponse(var20.getMessage());
//                sysLog.setResponseLength(0);
//
//                try {
//                    this.iSysLogService.save(sysLog);
//                } catch (Exception var17) {
//                    this.logger.error(var17.getMessage());
//                }
//
//                return o;
//            }
//        } catch (Exception var21) {
//            end = System.currentTimeMillis();
//            sysLog.setExecutionDuration((int) (end - start));
//            sysLog.setResponseTime(DateUtil.dateToStr());
//            sysLog.setCacheStatus(0);
//            sysLog.setResponseLength(0);
//
//            try {
//                this.iSysLogService.save(sysLog);
//            } catch (Exception var16) {
//                this.logger.error(var16.getMessage());
//            }
//
//            return o;
//        }
//    }
//}
