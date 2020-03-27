package com.test.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface UserActiveMapper {

    /**
     * 建表
     * @param createTabelSql
     */
    @Update("${createTabelSql}")
    void createTable(@Param("createTabelSql")String createTabelSql);
}
