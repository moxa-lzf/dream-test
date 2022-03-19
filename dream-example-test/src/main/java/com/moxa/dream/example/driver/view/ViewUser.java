package com.moxa.dream.example.driver.view;



import com.moxa.dream.example.driver.table.User;

import java.util.List;

//@View("user")
public class ViewUser extends User {

    private List<ViewDept> viewDeptList;

    private ViewDept viewDept;
}
