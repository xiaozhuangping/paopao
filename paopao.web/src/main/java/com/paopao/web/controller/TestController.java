package com.paopao.web.controller;

import com.paopao.service.service.TestService;
import com.paopao.service.service.UserInfoService;
import com.paopao.service.vo.UserInfoVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService userService;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public String login() {
        return "403";
    }

    @RequestMapping("/index")
    public String home() {
        return "home";
    }

    @RequiresPermissions("用户管理")
    @RequestMapping("/add")
    @ResponseBody
    public UserInfoVo add() {
        UserInfoVo userInfoVo = userInfoService.findUserInfoById(1);
        return userInfoVo;
    }

    @RequestMapping("/loginBy/{name}/{pwd}")
    public String loginBy(@PathVariable String name,@PathVariable String pwd) {
        logger.info("准备登陆用户 => {}", name);
        UsernamePasswordToken token = new UsernamePasswordToken(name,pwd);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + name + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + name + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,未知账户");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,错误的凭证");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            logger.info("用户[" + name + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            return "redirect:/index";
        } else {
            token.clear();
            return "redirect:/login";
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout() {
        logger.info("正在退出");
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        return "退出成功";
    }



}