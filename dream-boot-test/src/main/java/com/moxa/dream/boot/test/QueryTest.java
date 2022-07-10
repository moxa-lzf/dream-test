package com.moxa.dream.boot.test;

import com.moxa.dream.boot.BootApplication;
import com.moxa.dream.boot.service.CityService;
import com.moxa.dream.boot.table.City;
import com.moxa.dream.boot.template.mapper.TemplateMapper;
import com.moxa.dream.driver.page.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class QueryTest {
    @Autowired
    private CityService cityService;
    @Autowired
    private TemplateMapper templateMapper;
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
            templateMapper.selectById(City.class, 1);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void testSelectByIds() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            templateMapper.selectByIds(City.class, Arrays.asList(1, 2, 3));
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void existById(){
        long l = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            boolean b = templateMapper.existById(City.class, 12);
        }
        System.out.println(System.currentTimeMillis() - l);
    }

}
