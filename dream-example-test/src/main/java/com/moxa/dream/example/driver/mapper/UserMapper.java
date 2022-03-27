package com.moxa.dream.example.driver.mapper;

import com.moxa.dream.example.driver.view.MyViewUser;
import com.moxa.dream.module.annotation.Mapper;
import com.moxa.dream.module.annotation.Sql;

import java.util.List;

@Mapper("mapper.xml")
public interface UserMapper {
    MyViewUser selectUserById(int id);
    MyViewUser selectUserWithDeptById(int id);
    @Sql("select id,name,create_time from user")
    List<MyViewUser> selectUserList();

}