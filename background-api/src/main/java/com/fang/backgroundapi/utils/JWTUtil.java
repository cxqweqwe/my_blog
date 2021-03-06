package com.fang.backgroundapi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fang.backgroundapi.common.CommonInfo;
import com.fang.backgroundapi.pojo.DO.SysUsers;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bernie_fang
 * @Description: 使用JWT生成Token
 * @create 2021/8/17 0:56
 **/
@Slf4j
public class JWTUtil {

    /**
     * 2个小时过期时间
     */
    private static final long EXPIRE_TIME_2 = 2 * 60 * 60 * 1000;//2个小时

    /**
     * token的密钥
     */
    private static final String TOKEN_SECRET = "f26e587c28064d0e855e72c0a6a0e618";

    /**
     * 校验token是否正确
     */
    public static boolean verify(String token) throws IOException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (TokenExpiredException e) {
            log.error("token验证失败-------------->{}", "登录信息已过期");
            throw new RuntimeException("登录信息已过期，请重新登录");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("验证不通过,请重新登录");
            // return false;
        }
    }

    /**
     * 获取token中的信息无需密钥也能获得，这里获取登录者的id
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param token:
     * @Description: 获取token中的作者ID
     * @Author: Bernie_fang
     * @Since: 2021/8/18 23:09
     * @return: java.lang.String
     **/
    public static String getAuthorId(String token) {
        try {
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token);
            Claim authorityId = verify.getClaim("authorId");
            return authorityId.asString();
        } catch (Exception e) {
            log.error("token解析获取authorId出错------------->{}", e.getMessage());
            return null;
        }
    }


    /**
     * @param users:
     * @Description: 生成签名
     * @Author: Bernie_fang
     * @Since: 2021/8/18 23:08
     * @return: java.lang.String
     **/
    public static String signToToken(SysUsers users) {
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + CommonInfo.EXPIRATION_TIME_MILLISECOND);
            //密钥及算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //附带userId信息，生成签名
            return JWT.create()
                    .withHeader(header)
                    .withClaim("username", users.getUsername())
                    .withClaim("authorId", users.getAuthorId())
                    .withExpiresAt(date)  //设置过期时间
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Description: 验证原有token是否在两个小时以内过期,是返回true,否返回false
     * @Author: Bernie_fang
     * @Since: 2022/2/20 13:54
     * @param verifyToken:
     * @return: java.lang.Boolean
     **/
    public static Boolean verifyExpiresInTwoHours(String verifyToken) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(verifyToken);
        Date expiresAt = jwt.getExpiresAt();        //过期时间
        Long twoHours =  new Date().getTime() + EXPIRE_TIME_2;
        if (expiresAt.getTime() > twoHours){
            return false;
        }
        return true;
    }

    /**
     * @param username:
     * @param authorId:
     * @Description: 生成签名
     * @Author: Bernie_fang
     * @Since: 2021/8/18 23:08
     * @return: java.lang.String
     **/
    public static String signToToken(String username,String authorId) {
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + CommonInfo.EXPIRATION_TIME_MILLISECOND);
            //密钥及算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //附带userId信息，生成签名
            return JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("authorId", authorId)
                    .withExpiresAt(date)  //设置过期时间
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Description: 根据需要的时间生成token
     * @Author: Bernie_fang
     * @Since: 2022/3/3 22:37
     * @param username:
     * @param authorId:
     * @param EXPIRE_TIME: 过期时长
     * @return: java.lang.String
     **/
    public static String signToToken(String username,String authorId, Integer EXPIRE_TIME) {
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //密钥及算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //附带userId信息，生成签名
            return JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("authorId", authorId)
                    .withExpiresAt(date)  //设置过期时间
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

}
