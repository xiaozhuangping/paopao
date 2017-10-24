package com.paopao.impl.utils;

import com.paopao.service.vo.UserInfoVo;
import com.paopao.sql.vo.UserInfo;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * 实体类的转换 BeanMapperFactory
 * Created by Administrator on 2017/2/25/025.
 */
public class BeanMapperFactory {

    public static MapperFactory UserInfoVoToUserInfo = new DefaultMapperFactory.Builder().mapNulls(false).build();
    static {
        UserInfoVoToUserInfo.classMap(UserInfoVo.class, UserInfo.class).byDefault().register();
    }



}
