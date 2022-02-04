package com.example.springsecurityoauth2demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class Springsecurityoauth2demoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreateToken(){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("888")
                .setSubject("Rose")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "qqchen");
        String token = jwtBuilder.compact();
        System.out.println(token);
        System.out.println("-----------");
        /**
         * eyJhbGciOiJIUzI1NiJ9
         * .eyJqdGkiOiI4ODgiLCJzdWIiOiJSb3NlIiwiaWF0IjoxNjQzOTgyMzI4fQ
         * .Xixh9A-DhkrtH1hk9XifcaRCnXg5iEo-4PwhAGGqAeQ
         */
        String[] split = token.split("\\.");
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
    }

    @Test
    public void testCreateTokenHasExp(){
        long now = System.currentTimeMillis();
        long exp = now+60*1000;
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("888")
                .setSubject("Rose")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "qqchen")
                .setExpiration(new Date(exp));
        String token = jwtBuilder.compact();
        System.out.println(token);
        System.out.println("-----------");
    }
    @Test
    public void testParseTokenHasExp(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiJSb3NlIiwiaWF0IjoxNjQzOTgzMDk0LCJleHAiOjE2NDM5ODMxNTR9.WW5vLLT2-AHuV50B8hedZzMR-b7kAKyZ8C8nigXzELE";
        Claims claims = Jwts.parser()
                .setSigningKey("qqchen")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("id"+claims.getId());
        System.out.println("subject"+claims.getSubject());
        System.out.println("issued at"+claims.getIssuedAt());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("签发时间"+sf.format(claims.getIssuedAt()));
        System.out.println("过期时间"+sf.format(claims.getExpiration()));
        System.out.println("当前时间"+sf.format(new Date()));
    }
    @Test
    public void testCreateTokenHasExp2(){
        long now = System.currentTimeMillis();
        long exp = now+60*1000;
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("888")
                .setSubject("Rose")
                .setIssuedAt(new Date())
                .claim("username","admin")
                .claim("address","shanghai")
                .signWith(SignatureAlgorithm.HS256, "qqchen")
                .setExpiration(new Date(exp));
        String token = jwtBuilder.compact();
        System.out.println(token);
        System.out.println("-----------");
    }
    @Test
    public void testParseTokenHasExp2(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiJSb3NlIiwiaWF0IjoxNjQzOTgzNjUwLCJ1c2VybmFtZSI6ImFkbWluIiwiYWRkcmVzcyI6InNoYW5naGFpIiwiZXhwIjoxNjQzOTgzNzEwfQ.Vv8nk_69E1n_4OdQ4w8aPouC6hb4D8wj6QtEKTdWNZc";
        Claims claims = Jwts.parser()
                .setSigningKey("qqchen")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("id"+claims.getId());
        System.out.println("subject"+claims.getSubject());
        System.out.println("issued at"+claims.getIssuedAt());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("签发时间"+sf.format(claims.getIssuedAt()));
        System.out.println("过期时间"+sf.format(claims.getExpiration()));
        System.out.println("当前时间"+sf.format(new Date()));
        System.out.println("username:"+claims.get("username"));
        System.out.println("password:"+claims.get("password"));
    }
}
