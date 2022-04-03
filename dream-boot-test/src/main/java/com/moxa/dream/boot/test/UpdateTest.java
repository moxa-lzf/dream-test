package com.moxa.dream.boot.test;

import com.moxa.dream.boot.BootApplication;
import com.moxa.dream.boot.service.CityService;
import com.moxa.dream.boot.template.mapper.SessionMapper;
import com.moxa.dream.boot.table.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class UpdateTest {
    @Autowired
    private CityService cityService;
    @Autowired
    private SessionMapper sessionMapper;
    static int count = 1000000;

    @Test
    public void updateId() {
        long l = System.currentTimeMillis();
        City city=new City();
        city.setId(1);
        city.setName("name");
        city.setState("state");
        city.setCountry("country");
        for (int i = 0; i < count; i++) {
//            cityService.update(city);
            sessionMapper.updateById(city);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void updateNonId() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            City city=new City();
            city.setId(1);
            city.setCountry("country"+i);
            sessionMapper.updateNonById(city);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
