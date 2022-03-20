package com.moxa.dream.example.driver.table;

import com.moxa.dream.module.annotation.Column;
import com.moxa.dream.module.annotation.Id;
import com.moxa.dream.module.annotation.Table;

import java.sql.Timestamp;

@Table("user")
public class User {
    @Column
    @Id
    private long id;
    @Column
    private String name;
    @Column
    private Timestamp createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
