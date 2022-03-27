package com.moxa.dream.example.driver.view;

import com.moxa.dream.module.annotation.View;

import java.sql.Timestamp;
import java.util.List;

@View("user")
public class MyViewUser {
    private int id;
    private String name;
    private Timestamp createTime;
    private List<ViewDept> viewDeptList;
}
