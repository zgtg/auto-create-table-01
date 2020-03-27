package com.test.mybatis.service;

import com.test.mybatis.mapper.UserActiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 */
@Service
public class UserActiveService {
    /**
     * 日活表前缀
     */
    public static final String USER_ACTIVE_TABLE_PREFIX = "tb_user_active_";

    @Autowired(required = false)
    UserActiveMapper userActiveMapper;

    /**
     * 定时器(每天凌晨1点执行一次)
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void createTableJob() {
        int num = 3;

        for(int i=1; i<=num; i++) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, i);

            createTable(c.getTime());
        }
    }

    /**
     * 获取表名
     * @param date
     * @return
     */
    public String getTableName(Date date) {
        return USER_ACTIVE_TABLE_PREFIX + new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * 创建表
     * @param date
     */
    public void createTable(Date date) {
        String sql = "create table if not exists " + getTableName(date) + "(\n" +
                    "    id int(11) primary key auto_increment comment 'id',\n" +
                    "    user_id int(11) comment '用户ID',\n" +
                    "    active_time datetime comment '活跃时间'\n" +
                    ")";
        userActiveMapper.createTable(sql);
    }
}
