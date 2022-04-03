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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class InsertTest {
    @Autowired
    private SessionMapper sessionMapper;
    static int count = 1000000;
    @Autowired
    private CityService cityService;
    @Test
    public void testInsert() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            City city=new City();
//            city.setId(i+10);
            city.setName("name"+i);
//            city.setCountry("country"+i);
            city.setState("state"+i);
            sessionMapper.insert(city);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void testInsertBatch() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count/100; i++) {
            List<City> cityList=new ArrayList<>();
            for(int k=0;k<100;k++) {
                City city = new City();
                city.setId(i*100 +k+ 10);
                city.setName("name" + i);
                city.setCountry("country" + i);
                city.setState("state" + i);
                cityList.add(city);
            }
            cityService.insertBatch(cityList);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void testInsertMany() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count/100; i++) {
            List<City> cityList=new ArrayList<>();
            for(int k=0;k<100;k++) {
                City city = new City();
                city.setId(i*100 +k+ 10);
                city.setName("name" + i);
                city.setCountry("country" + i);
                city.setState("state" + i);
                cityList.add(city);
            }
            sessionMapper.insertMany(cityList);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
