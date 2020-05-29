package com.ylgh.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

/**
 * @ 作者：上善若水
 * @ 时间：2020-04-19 23:07
 * @ 描述：token的产生和解析
 * @ 修改： 于 年 月 日更改
 * @ 版本:1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class TokenUtil {
    //签名私钥:gjf.33731
    private static String key = "e7f91be3b4293af6e4f71cf8484d8cfd";

    /**
     * @ 作者：上善若水
     * @ 时间：2020/4/20 13:16
     * @ 描述：用于生成token id:唯一标识 name:签发者 map：自定义Claims集合
     * @ 修改： 于 年 月 日更改
     * @ 版本:1.0
     */
    public static String createToken(String account, String nickName) {
        Calendar nowDate = Calendar.getInstance();
        nowDate.add(Calendar.MINUTE,240);
        Date expiration = nowDate.getTime();
        JwtBuilder jwtBuilder = Jwts.builder().setId(account)
                .setSubject(nickName)
                .setIssuedAt(new Date())//签发时间
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, key);
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * @ 作者：上善若水
     * @ 时间：2020/4/20 13:19
     * @ 描述：解析token字符串
     * @ 修改： 于 年 月 日更改
     * @ 版本:1.0
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

    }
}
