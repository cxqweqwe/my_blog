package com.fang.backgroundapi.shiro;

import com.fang.backgroundapi.common.CommonInfo;
import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.fang.backgroundapi.service.impl.SysUsersAuthorityServiceImpl;
import com.fang.backgroundapi.service.impl.SysUsersServiceImpl;
import com.fang.backgroundapi.utils.JWTUtil;
import com.fang.backgroundapi.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Bernie_fang
 * @Description: 定义shiro框架：认证授权
 * @create 2021/8/17 0:56
 **/
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SysUsersServiceImpl sysUsersService;

    @Autowired
    private SysUsersAuthorityServiceImpl authorityService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 权限认证登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();//获取token
        String authorId = JWTUtil.getAuthorId(token);//解密，获取userId
        // log.info("账号验证===============>{}", username);

        SysUsers currentUser = (SysUsers)redisUtils.get(CommonInfo.SYS_USER + authorId);
        if (currentUser == null){
            currentUser = sysUsersService.findUserById(authorId);
        }
        try {
            //通过认证后返回
            if (currentUser != null && JWTUtil.verify(token)){
                this.RefreshRedisUser(authorId);
                return new SimpleAuthenticationInfo(token,token,this.getName());
            }
        } catch (Exception e) {
            log.error("用户验证出错-------------->{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 授权处理：保存用户角色
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = principalCollection.toString();
        String authorId = JWTUtil.getAuthorId(token);
        // log.info("用户授权===============>{}", authorId);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> role = new HashSet<>();
        List<String> authorIds = authorityService.querySysUsersAuthorityByAuthorId(authorId);
        role.addAll(authorIds);
        authorizationInfo.setRoles(role);
        // log.info("用户授权通过===============>{}", authorId);
        return authorizationInfo;
    }

    public void RefreshRedisUser(String authorId){
        // long expire_before = redisUtils.getExpire(CommonInfo.SYS_USER + authorId);
        // log.info("更新前过期时间，{}", new Date(expire_before));
        // 更新过期时间
        redisUtils.expire(CommonInfo.SYS_USER + authorId, new Date().getTime() + CommonInfo.EXPIRATION_TIME_SECOND);
        // long expire_after = redisUtils.getExpire(CommonInfo.SYS_USER + authorId);
        // log.info("更新后过期时间，{}", new Date(expire_after));
        // log.info("两个时间相隔---{}分钟", (expire_after - expire_before) / 1000);
        // log.info("两个时间相隔---{}小时", (expire_after - expire_before) / 1000 / 60);
    }


}
