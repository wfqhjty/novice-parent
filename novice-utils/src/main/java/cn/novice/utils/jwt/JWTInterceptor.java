package cn.novice.utils.jwt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每个请求都检测header
        String authorization = request.getHeader("Authorization");
        //页面跳转后增加token，如果有则放通
        if (StringUtils.isBlank(authorization)) {
            authorization = request.getParameter("tc");
        }
        String contexPath = request.getContextPath();
        if (authorization == null) {
            response.sendRedirect(contexPath + "/pages/login/login.html");
            return false;
        } else
            return true;
    }
}
