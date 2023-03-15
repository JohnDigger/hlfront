package com.hlfront.utils;

/**
 * @author 贾佳
 * @date 2023/3/15 21:23
 */

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 进行验证码校验的过滤器
 */
@Component
public class SendSmsFilter extends OncePerRequestFilter {


    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        //请求路径为Smslogin，并且为post类型
        if (StringUtils.equals("/sms/PhoneLogin",request.getRequestURI())
                && StringUtils.equals(request.getMethod(),"post")){
            //获取手机号
            String phone = request.getParameter("phoneNumber");
            //获取验证码
            String code = request.getParameter("Code");
            //获取redis中保存的验证码
            String code_redis = (String) redisTemplate.opsForValue().get(phone);
            if (code_redis != null && code_redis.equals(code)) {
                //删除redis中保存的验证码
                redisTemplate.delete(phone);
                System.out.println("验证码输入正确");
            }else {
                throw new InternalAuthenticationServiceException("验证码错误");
            }

        }
        chain.doFilter(request, response);
    }
}