package com.paopao.web.config.shiro;

import com.paopao.service.vo.RmUserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Component
public class MyShiroRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Map<String, Object> map = new HashMap<>();
        map.put("userName", token.getUsername());
        map.put("passWord", token.getPassword());
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
        /*Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_id", userId);
        List<SysRole> roleList = sysRoleService.selectByMap(map);
        Set<String> roleSet = new HashSet<String>();
        for(SysRole role : roleList){
            roleSet.add(role.getType());
        }*/
        //实际开发，当前登录用户的角色和权限信息是从数据库来获取的，我这里写死是为了方便测试
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("123");
        info.setRoles(roleSet);
        //根据用户ID查询权限（permission），放入到Authorization里。
        /*List<SysPermission> permissionList = sysPermissionService.selectByMap(map);
        Set<String> permissionSet = new HashSet<String>();
        for(SysPermission Permission : permissionList){
            permissionSet.add(Permission.getName());
        }*/
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("update");
        info.setStringPermissions(permissionSet);
        return info;
    }
}
