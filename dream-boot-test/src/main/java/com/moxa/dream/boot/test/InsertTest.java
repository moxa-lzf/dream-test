package com.moxa.dream.boot.test;

import com.moxa.dream.boot.BootApplication;
import com.moxa.dream.boot.mapper.UserMapper;
import com.moxa.dream.boot.service.UserService;
import com.moxa.dream.boot.table.User;
import com.moxa.dream.boot.template.mapper.TemplateMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class InsertTest {
    @Autowired
    private TemplateMapper templateMapper;
    static int count = 1000000;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testInsert() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            User user =new User();
            user.setId(i+10);
            user.setName("name"+i);
            user.setAge(i);
            user.setEmail("email"+i);
            templateMapper.insert(user);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void testInsertBatch() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count/100; i++) {
            List<User> userList =new ArrayList<>();
            for(int k=0;k<100;k++) {
                User user = new User();
                user.setId(i*100 +k+ 10);
                user.setName("name" + i);
                user.setAge(i);
                user.setEmail("email" + i);
                userList.add(user);
            }
            userService.insertBatch(userList);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void testInsertMany() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count/100; i++) {
            List<User> userList =new ArrayList<>();
            for(int k=0;k<100;k++) {
                User user = new User();
                user.setId(i*100 +k+ 10);
                user.setName("name" + i);
                user.setAge( i);
                user.setEmail("email" + i);
                userList.add(user);
            }
            templateMapper.insertMany(userList);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void testInsertMany2() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count/100; i++) {
            List<User> userList =new ArrayList<>();
            for(int k=0;k<100;k++) {
                User user = new User();
                user.setId(i*100 +k+ 10);
                user.setName("name" + i);
                user.setAge( i);
                user.setEmail("email" + i);
                userList.add(user);
            }
            userService.insertBatch2(userList);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
