package com.paopao.web.config.shiro;

import com.paopao.service.service.UserInfoService;
import com.paopao.service.vo.RmUserInfo;
import com.paopao.service.vo.SysPermission;
import com.paopao.service.vo.SysRole;
import com.paopao.service.vo.UserInfoVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
@Component
public class MyShiroRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
    @Resource
    private UserInfoService userInfoService;
    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        RmUserInfo rmUserInfo = new RmUserInfo();
        rmUserInfo.setName(token.getUsername());
        rmUserInfo.setPassword(token.getPassword().toString());
        if (null == token.getPassword()) {
            throw new AccountException("帐号或密码不正确！");
        } else if (token.getUsername() == "禁用") {
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
            throw new DisabledAccountException("帐号已经禁止登录！");
        }
        return new SimpleAuthenticationInfo(rmUserInfo, token.getPassword(), getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        logger.info("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        RmUserInfo token = (RmUserInfo) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。
        UserInfoVo userInfoVo = userInfoService.findUserInfoById(token.getUid());
        List<SysRole> roleList = userInfoVo.getRoleList();
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        for(SysRole role : roleList){
            roleSet.add(role.getRole());
            logger.info("角色："+role.getRole());
            //根据用户ID查询权限（permission），放入到Authorization里。
            for (SysPermission sysPermission : role.getPermissions()){
                logger.info("权限："+sysPermission.getName());
                permissionSet.add(sysPermission.getName());
            }
        }
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}
