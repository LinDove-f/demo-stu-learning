package com.attust.mp.interceptor;

import com.attust.mp.common.TokenStore;
import com.attust.mp.vo.LoginVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author fqlStart
 * {@code @create} 2026-06-12-9:11
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String token = request.getHeader("token");

        if(token == null || token.trim().isEmpty()){
            response.setStatus(401);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"未登录，请先登录\",\"data\":null}");
            return false;
        }

        LoginVO loginVO = TokenStore.get(token);

        if (loginVO == null) {
            response.setStatus(401);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"登录已过期或 token 无效\",\"data\":null}");
            return false;
        }

        return  true;
    }

}
