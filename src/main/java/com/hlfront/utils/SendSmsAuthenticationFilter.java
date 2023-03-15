package com.hlfront.utils;

/**
 * @author 贾佳
 * @date 2023/3/15 21:30
 */

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * UsernamePasswordAuthenticationFilter是AbstractAuthenticationProcessingFilter针对使用用户名和密码进行身份验证而定制化的一个过滤器。
 *
 *
 * 模拟 UsernamePasswordAuthenticationFilter类使用手机号进行身份验证的一个过滤器
 */
public class SendSmsAuthenticationFilter extends
        AbstractAuthenticationProcessingFilter {


    public static final String SPRING_SECURITY_FORM_TELEPHONE_KEY = "telephone";

    //验证短信验证码，传递手机号的参数的名称【telephone】
    private String telephoneParameter = SPRING_SECURITY_FORM_TELEPHONE_KEY;
    //指定请求时post形式
    private boolean postOnly = true;


    public SendSmsAuthenticationFilter() {
        //拦截访问路径，访问请求方式
        super(new AntPathRequestMatcher("/Smslogin", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String telephone = obtainTelephone(request);

        if (telephone == null) {
            telephone = "";
        }


        telephone = telephone.trim();


        //自定义SendSmsAuthenticationToken类实现其方法
        SendSmsAuthenticationToken authRequest = new SendSmsAuthenticationToken(
                telephone);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainTelephone(HttpServletRequest request) {
        return request.getParameter(telephoneParameter);
    }

    /*
     * 功能描述：提供身份验证请求的详细属性
     * 入参：[request 为此创建身份验证请求, authRequest 详细信息集的身份验证请求对象]
     */
    protected void setDetails(HttpServletRequest request,
                              SendSmsAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }


    /*
     * 功能描述：设置用于获取用户名的参数名的登录请求。
     * 入参：[telephoneParameter 默认为“用户名”。]
     */
    public void setTelephoneParameter(String telephoneParameter) {
        Assert.hasText(telephoneParameter, "Telephone parameter must not be empty or null");
        this.telephoneParameter = telephoneParameter;
    }


    /**
     * 功能描述：定义此筛选器是否只允许HTTP POST请求。如果设置为true，则接收不到POST请求将立即引发异常并不再继续身份认证
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getTelephoneParameter() {
        return telephoneParameter;
    }

}