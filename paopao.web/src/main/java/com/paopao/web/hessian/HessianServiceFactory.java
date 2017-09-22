package com.paopao.web.hessian;

import com.paopao.service.service.TestService;
import com.paopao.service.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class HessianServiceFactory {

    @Bean
    public HessianProxyFactoryBean TestService(){
        HessianProxyFactoryBean hessianProxyFactoryBean = new HessianProxyFactoryBean();
        hessianProxyFactoryBean.setConnectTimeout(10000); //连接超时10秒
        hessianProxyFactoryBean.setReadTimeout(20000);//请求超时20秒
        hessianProxyFactoryBean.setServiceUrl("http://127.0.0.1:80/testService");
        hessianProxyFactoryBean.setServiceInterface(TestService.class);
        return hessianProxyFactoryBean;
    }

    @Bean
    public HessianProxyFactoryBean userInfoService(){
        HessianProxyFactoryBean hessianProxyFactoryBean = new HessianProxyFactoryBean();
        hessianProxyFactoryBean.setConnectTimeout(10000); //连接超时10秒
        hessianProxyFactoryBean.setReadTimeout(20000);//请求超时20秒
        hessianProxyFactoryBean.setServiceUrl("http://127.0.0.1:80/userInfoService");
        hessianProxyFactoryBean.setServiceInterface(UserInfoService.class);
        return hessianProxyFactoryBean;
    }
}

