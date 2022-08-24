package com.gly.springboot.config.security.filter;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.gly.springboot.config.security.entity.SecurityConstants;
import com.gly.springboot.config.security.utils.JwtToken;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Shiro自定义auth过滤器
 *
 * @author EugeneGe
 * @date 2022-08-24 8:10
 */
@Component
public class MyAuthFilter extends BasicHttpAuthenticationFilter {

    private final static Logger logger = LoggerFactory.getLogger(MyAuthFilter.class);

    /**
     * 不需要验证的接口
     * 直接注入失败，静态变量方式注入
     */
    private static
    String anonUrl;

    @Value("${my.shiro.anonUrl}")
    public void setAnonUrl(String anonUrl) {
        MyAuthFilter.anonUrl = anonUrl;
    }

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String[] anonUrls = anonUrl.split(StringPool.COMMA);
        boolean match = false;
        for (String u : anonUrls) {
            if (pathMatcher.match(u, httpServletRequest.getRequestURI())) {
                match = true;
                break;
            }
        }
        if (match) {
            return true;
        }
        if (isLoginAttempt(request, response)) {
            return executeLogin(request, response);
        }
        return false;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(SecurityConstants.AUTHENTICATION);
        return token != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(SecurityConstants.AUTHENTICATION);
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            getSubject(request, response).login(jwtToken);
            // 如果没有抛出异常则代表登入成功，返回true
            return true;
        } catch (Exception e) {
//            response.setCharacterEncoding("utf-8");
//            response.getWriter().print("error");
            logger.error(e.getMessage());
            return false;
        }

    }

    /**
     * 对跨域提供支持
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 是否支持cookie跨域
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        // 支持跨域
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个 option请求，这里我们给 option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 防止token过期前端弹出登录框
     * 返回401错误码  前端跳转到登录页
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        logger.debug("Authentication required: sending 401 Authentication challenge response.");
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        final String message = "未认证，请在前端系统进行认证";
        try (PrintWriter out = httpResponse.getWriter()) {
            String responseJson = "{\"message\":\"" + message + "\"}";
            out.print(responseJson);
        } catch (IOException e) {
            logger.error("sendChallenge error：", e);
        }
        return false;
    }
}
