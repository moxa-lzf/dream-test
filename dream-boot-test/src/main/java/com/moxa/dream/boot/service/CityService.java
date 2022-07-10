package com.moxa.dream.boot.service;

import com.moxa.dream.boot.mapper.CityMapper;
import com.moxa.dream.boot.table.City;
import com.moxa.dream.boot.template.mapper.TemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private TemplateMapper templateMapper;

    public City findByState(String state) {
        City city = cityMapper.findByState(state);
        return city;
    }

    public Integer update(City city) {
        return cityMapper.update(city);
    }

    @Transactional
    public void insertBatch(List<City> cityList) {
        templateMapper.insertBatch(cityList);
    }
}
