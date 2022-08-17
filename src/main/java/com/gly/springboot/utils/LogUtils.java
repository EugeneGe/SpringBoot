package com.gly.springboot.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author EugeneGe
 * @date 2021-05-25 17:34
 */
public class LogUtils {

    public LogUtils() {
    }

//    public static SysLog getLog(HttpServletRequest request, String loginName) {
//        SysLog log = new SysLog();
//        log.setIp(getCliectIp(request));
//        Map<String, String> map = new HashMap<String, String>();
//        log.setMethod(request.getMethod());
//        log.setUrl(request.getRequestURL().toString());
//        Enumeration headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String key = (String) headerNames.nextElement();
//            String value = request.getHeader(key);
//            map.put(key, value);
//        }
//        log.setHeaders(map.toString());
//        log.setRequestTime(DateUtil.dateToStr());
//        if (loginName != null) {
//            log.setLoginAccount(loginName);
//        } else {
//            log.setLoginAccount("null");
//        }
//        return log;
//    }

    /**
     * 获取客户端ip地址
     *
     * @param request
     * @return
     */
    public static String getCliectIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip != null && ip.trim() != "" && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (ip != null && ip.trim() != "" && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
