package com.moxa.dream.example.driver.table;


import com.moxa.dream.system.annotation.Column;
import com.moxa.dream.system.annotation.Table;

@Table
public class Dept {
    @Column
    private int id;
    @Column
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
