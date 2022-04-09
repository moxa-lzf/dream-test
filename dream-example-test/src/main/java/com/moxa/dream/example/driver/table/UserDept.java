package com.moxa.dream.example.driver.table;

import com.moxa.dream.system.annotation.Column;
import com.moxa.dream.system.annotation.Join;
import com.moxa.dream.system.annotation.Table;

@Table
public class UserDept {
    @Column
    private int id;
    @Column
    private int userId;
    @Column
    private int deptId;
    @Join(column = "user_id",joinColumn = "id",joinType = Join.JoinType.INNER_JOIN)
    private User user;
    @Join(column = "dept_id",joinColumn = "id")
    private Dept dept;
}
