package com.paopao.sql.dao;

import com.paopao.sql.vo.PaoPaoSql;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaoPaoSqlDao extends JpaRepository<PaoPaoSql,Integer> {
}
