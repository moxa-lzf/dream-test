package com.moxa.dream.boot.service;

import com.moxa.dream.boot.mapper.CityMapper;
import com.moxa.dream.boot.spring.annotation.SessionControl;
import com.moxa.dream.boot.table.City;
import com.moxa.dream.driver.page.annotation.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private CityMapper cityMapper;
    public City findByState(String state) {
        City city = cityMapper.findByState(state);
        return city;
    }
}
