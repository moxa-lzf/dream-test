
package com.moxa.dream.boot.table;

import com.moxa.dream.system.annotation.Column;
import com.moxa.dream.system.annotation.Id;
import com.moxa.dream.system.annotation.Table;
import com.moxa.dream.system.annotation.View;
@View("city")
@Table
public class City {
    @Id
    @Column
    private Integer id;
    @Column
//    @LeftLike("name")
    private String name;
//    @Equal("state")
    @Column
    private String state;
    @Column
    private String country;

    public Integer getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
