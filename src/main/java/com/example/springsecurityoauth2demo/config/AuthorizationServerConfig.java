package com.example.springsecurityoauth2demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;


/**
 * 授权服务器配置
 * @author mac
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
//                配置clientid(开发者账号id)
                .withClient("admin")
//                配置client secret（开发者id对应的密码）
                .secret(passwordEncoder.encode("112233"))
//                配置redirect_uri,用于授权成功后跳转（重定向的uri）
                .redirectUris("https://www.baidu.com")
//                配置申请的权限范围（授权范围）
                .scopes("all")
//                配置grant_type，表示授权类型
                .authorizedGrantTypes("authorization_code");
    }

//    http://localhost:8080/oauth/authorize?response_type=code&client_id=admin&redirect_uri=https://www.baidu.com&scope=all
}
