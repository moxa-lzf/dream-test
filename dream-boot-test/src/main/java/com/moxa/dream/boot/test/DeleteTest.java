package com.moxa.dream.boot.test;

import com.moxa.dream.boot.BootApplication;
import com.moxa.dream.boot.service.CityService;
import com.moxa.dream.boot.spring.mapper.SessionMapper;
import com.moxa.dream.boot.table.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class DeleteTest {
    @Autowired
    private SessionMapper sessionMapper;
    static int count = 1000000;

    @Test
    public void deleteId() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            sessionMapper.deleteById(City.class,1);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
