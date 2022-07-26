package com.moxa.dream.boot.test;

import com.moxa.dream.boot.BootApplication;
import com.moxa.dream.boot.table.User;
import com.moxa.dream.boot.template.mapper.TemplateMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class DeleteTest {
    @Autowired
    private TemplateMapper templateMapper;
    static int count = 1000000;

    @Test
    public void deleteById() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            templateMapper.deleteById(User.class,1);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void deleteByIds() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            templateMapper.deleteByIds(User.class, Arrays.asList(1, 2, 3));
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void deleteById2() {
        templateMapper.deleteByIds(User.class, Arrays.asList(1, 2, 3,4,5,6));
    }
}
