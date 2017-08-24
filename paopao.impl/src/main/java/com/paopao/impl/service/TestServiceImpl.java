package com.paopao.impl.service;

import com.paopao.impl.hessian.HessianService;
import com.paopao.service.TestService;
import com.paopao.sql.dao.PaoPaoSqlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@HessianService
public class TestServiceImpl implements TestService{

    @Autowired
    private PaoPaoSqlDao paoPaoSqlDao;

    @Override
    public List findAll() {
        return paoPaoSqlDao.findAll();
    }
}
