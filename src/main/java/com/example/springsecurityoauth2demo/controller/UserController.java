package com.example.springsecurityoauth2demo.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("user")
public class UserController {


    /**
     * 获取登录的用户信息
     * @param authentication
     * @return
     */
    @GetMapping("getCurrentUser")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.substring(header.indexOf("bear") + 7);
        return Jwts.parser()
                .setSigningKey("test_key".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
//        return authentication.getPrincipal();
    }
}
