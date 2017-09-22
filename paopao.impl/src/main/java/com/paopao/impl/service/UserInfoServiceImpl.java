package com.paopao.impl.service;

import com.paopao.impl.hessian.HessianService;
import com.paopao.impl.utils.BeanMapperFactory;
import com.paopao.service.service.UserInfoService;
import com.paopao.service.vo.UserInfoVo;
import com.paopao.sql.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@HessianService
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfoVo findUserInfoById(Integer id) {
      return BeanMapperFactory.UserInfoVoToUserInfo.getMapperFacade().map(userInfoDao.findOne(1),UserInfoVo.class);
    }

}
