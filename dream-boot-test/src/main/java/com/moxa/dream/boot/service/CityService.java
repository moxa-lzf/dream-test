package com.moxa.dream.boot.service;

import com.moxa.dream.boot.mapper.CityMapper;
import com.moxa.dream.boot.table.City;
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
