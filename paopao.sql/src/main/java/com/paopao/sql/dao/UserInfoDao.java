package com.paopao.sql.dao;

import com.paopao.sql.vo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {

}
