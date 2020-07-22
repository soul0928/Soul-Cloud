package com.soul.auth.config.handler;

import com.soul.common.core.utils.result.ResponseUtil;
import com.soul.common.core.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录失败
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2020/7/22 13:49
 **/
@Slf4j
@Component
public class SoulAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * @description  身份验证失败时调用
     * @author 王栋
     * @date 2019/10/29 14:16
     * @param request
     * @param response
     * @param exception
     * @return void
     **/
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Result<Object> result = null;
        //返回json数据
        if (exception instanceof AccountExpiredException) {
            //账号过期
            result = Result.fail(HttpStatus.UNAUTHORIZED.value(), "账号已过期!!!");
        } else if (exception instanceof BadCredentialsException) {
            //密码错误
            result = Result.fail(HttpStatus.UNAUTHORIZED.value(), "密码错误!!!");
        } else if (exception instanceof CredentialsExpiredException) {
            //密码过期
            result = Result.fail(HttpStatus.UNAUTHORIZED.value(), "密码过期!!!");
        } else if (exception instanceof DisabledException) {
            //账号不可用
            result = Result.fail(HttpStatus.UNAUTHORIZED.value(), "账号不可用!!!");
        } else if (exception instanceof LockedException) {
            //账号锁定
            result = Result.fail(HttpStatus.UNAUTHORIZED.value(), "账号锁定!!!");
        } else if (exception instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = Result.fail(HttpStatus.UNAUTHORIZED.value(), "用户不存在!!!");
        }else{
            //其他错误
            result = Result.fail(exception.getMessage());
        }
        ResponseUtil.out(response, result);
    }

}
