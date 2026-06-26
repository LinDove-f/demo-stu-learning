package com.attust.mp.interceptor;

import com.attust.mp.module.entity.SysUserTokenEntity;
import com.attust.mp.module.mapper.SysUserTokenMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

/**
 * @author fqlStart
 * {@code @create} 2026-06-12-9:11
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        log.info("[Flow-1] LoginInterceptor.preHandle, url={}, method={}",
                request.getRequestURI(),
                request.getMethod());

        String token = request.getHeader("token");

        if(token == null || token.trim().isEmpty()){
            log.warn("[AUTH] token 为空，请求被拦截，uri={}", request.getRequestURI());

            response.setStatus(401);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"未登录，请先登录\",\"data\":null}");
            return false;
        }

        LambdaQueryWrapper<SysUserTokenEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserTokenEntity::getToken, token)
                .eq(SysUserTokenEntity::getStatus, 1)
                .gt(SysUserTokenEntity::getExpireTime, LocalDateTime.now());

        SysUserTokenEntity tokenEntity = sysUserTokenMapper.selectOne(wrapper);

        if (tokenEntity == null) {
            log.warn("[AUTH] token 无效，请求被拦截，uri={}", request.getRequestURI());

            response.setStatus(401);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"登录已过期或 token 无效\",\"data\":null}");
            return false;
        }

        log.info("[AUTH] token 校验通过，请求放行，uri={}", request.getRequestURI());

        return  true;
    }

}
