package com.moxa.dream.boot.mapper;

import com.moxa.dream.boot.table.User;
import com.moxa.dream.boot.view.UserView;
import com.moxa.dream.driver.page.Page;
import com.moxa.dream.driver.page.annotation.PageQuery;
import com.moxa.dream.system.annotation.Mapper;
import com.moxa.dream.system.annotation.Param;
import com.moxa.dream.system.annotation.Result;
import com.moxa.dream.system.annotation.Sql;

import java.util.List;

@Mapper("mapper/UserMapper.xml")
public interface UserMapper {
    @Sql("select id, name, age,email from user where name = @$(name)")
    User findByName(String name);
    @Sql("select @all(),'hello' name from user")
    List<User> findAll();
    @Sql("select id, name, age,email from user")
    @Result(rowType = List.class,colType = User.class)
    Object findByAll();
    @Sql("select id, name, age,email from user")
    @PageQuery
    List<User> findByPage(@Param("page") Page page);
    @Sql("select id, name, age,email from user where name = @rep(name)")
    User findByName2(String name);
    @Sql("delete from user where id in (@foreach(list))")
    int delete(List<Integer> idList);
    @Sql("update user set name=null where state = @$(state)")
    void updateCity(String state);

    @Sql("update user set name=@$(name),email=@$(email) where id=@$(id)")
    Integer update(User user);

    @Sql("update user set @non(name=@$(name),age=@$(age),email=@$(email)) where id=@$(id)")
    Integer updateNon(User user);

    @Sql("insert into user(id,name,age,email)values @foreach(list,(@$(item.id),@$(item.name),@$(item.age),@$(item.email)))")
    Integer insertMany(@Param("list") List<User> userList);

    @Sql("insert into user(id,name,age,email)values(@$(item.id),@$(item.name),@$(item.age),@$(item.email))")
    Integer insert(@Param("item") User user);

    List<UserView>selectAll();
}
