package com.hlfront.utils;

/**
 * @author 贾佳
 * @date 2023/3/15 21:31
 */

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 *
 * 模拟 UsernamePasswordAuthenticationToken类
 *
 */
public class SendSmsAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 510L;

    //存放认证信息，认证之前放的是手机号，认证之后UserDetails
    private final Object principal;


    /**
     * 功能描述：创建用户身份验证令牌需要用到此构造函数
     * 返回值：通过身份验证的代码返回false
     */
    public SendSmsAuthenticationToken(Object principal) {
        super((Collection)null);
        this.principal = principal;
        this.setAuthenticated(false);
    }

    /**
     * 功能描述：产生身份验证令牌
     * @param principal
     * @param authorities
     */
    public SendSmsAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }


    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
    @Override
    public Object getCredentials() {
        return null;
    }
}