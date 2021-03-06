package com.moxa.dream.boot.view;

import com.moxa.dream.boot.table.Blog;
import com.moxa.dream.system.annotation.View;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.List;

@View("user")
public class UserView {
    private Integer id;
    private String name;
    @Ignore
    private String email;
    @Ignore
    private List<Blog> blogList;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
