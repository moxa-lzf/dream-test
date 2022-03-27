package com.moxa.dream.example.driver.mapper;

import com.moxa.dream.example.driver.view.ViewDept;
import com.moxa.dream.module.annotation.Mapper;
import com.moxa.dream.module.annotation.Sql;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Sql("select @all(d) from dept d inner join user_dept ud on ud.dept_id=d.id where ud.user_id=@$(id)")
    List<ViewDept> selectDeptByUserId(int id);
}
