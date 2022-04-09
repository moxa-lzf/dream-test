package com.moxa.dream.example.driver.mapper;

import com.moxa.dream.driver.page.Page;
import com.moxa.dream.driver.page.annotation.PageQuery;
import com.moxa.dream.example.driver.view.MyViewUser;
import com.moxa.dream.system.annotation.Mapper;
import com.moxa.dream.system.annotation.Sql;

import java.util.List;

@Mapper("mapper.xml")
public interface UserMapper {
    MyViewUser selectUserById(int id);

    MyViewUser selectUserWithDeptById(int id);

    @Sql("select id,name,create_time from user")
    List<MyViewUser> selectUserList();

    @PageQuery("")
    @Sql("select id,name,create_time from user")
    Page<MyViewUser> selectPageUserList(Page page);

}
