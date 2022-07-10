package com.moxa.dream.boot.mapper;

import com.moxa.dream.boot.table.City;
import com.moxa.dream.system.annotation.Mapper;
import com.moxa.dream.system.annotation.Sql;

@Mapper
public interface CityMapper {
    @Sql("select id, name, state, country from city where state = @$(state)")
    City findByState(String state);

    @Sql("update city set name=null where state = @$(state)")
    void updateCity(String state);

    @Sql("update city set name=@$(name),state=@$(state) where id=@$(id)")
    Integer update(City city);
}
