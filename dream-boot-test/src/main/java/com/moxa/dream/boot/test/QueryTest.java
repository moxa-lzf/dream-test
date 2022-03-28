package com.moxa.dream.boot.test;

import com.moxa.dream.boot.BootApplication;
import com.moxa.dream.boot.service.CityService;
import com.moxa.dream.boot.template.mapper.SessionMapper;
import com.moxa.dream.boot.table.City;
import com.moxa.dream.driver.page.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class QueryTest {
    @Autowired
    private CityService cityService;
    @Autowired
    private SessionMapper sessionMapper;
    static int count = 1000000;

    @Test
    public void test() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            City city = cityService.findByState("CA");
        }
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void testSelectById() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            sessionMapper.selectById(City.class, 1);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void testSelectByIds() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            sessionMapper.selectByIds(City.class, List.of(1,2,3));
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void testSelectList(){
        long l = System.currentTimeMillis();
        City city=new City();
            city.setId(1);
//        city.setState("CA");
        for (int i = 0; i < count; i++) {
            List<City> cities = sessionMapper.selectList(City.class, city);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void testSelectPage(){
        long l = System.currentTimeMillis();
        City city=new City();
        for (int i = 0; i < count; i++) {
            Page page=Page.of(1,10);
            Page<City> cities = sessionMapper.selectPage(City.class, city, page);
        }
        System.out.println(System.currentTimeMillis() - l);
    }

}
