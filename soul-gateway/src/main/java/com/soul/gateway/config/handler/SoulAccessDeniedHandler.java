package com.soul.gateway.config.handler;

import com.soul.common.core.utils.result.ResponseUtil;
import com.soul.common.core.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限不足拦截
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2020/7/22 00:14
 **/
@Slf4j
@Component
public class SoulAccessDeniedHandler implements AccessDeniedHandler {

    public static final String INSUFFICIENT_PERMISSIONS_MSG = "权限不足！！！";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)  {
        ResponseUtil.out(response, Result.fail(HttpStatus.FORBIDDEN.value(), INSUFFICIENT_PERMISSIONS_MSG));
    }
}
