package com.moxa.dream.boot.test;

import com.moxa.dream.boot.BootApplication;
import com.moxa.dream.boot.mapper.UserMapper;
import com.moxa.dream.boot.service.UserService;
import com.moxa.dream.boot.template.mapper.TemplateMapper;
import com.moxa.dream.boot.table.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class UpdateTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TemplateMapper templateMapper;
    static int count = 1000000;

    @Test
    public void updateId() {
        long l = System.currentTimeMillis();
        User user =new User();
        user.setId(1);
        user.setName("name");
        user.setAge(1);
        user.setEmail("email");
        for (int i = 0; i < count; i++) {
//            cityService.update(city);
            templateMapper.updateById(user);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void updateNonId() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            User user =new User();
            user.setId(1);
            user.setEmail("country"+i);
            templateMapper.updateNonById(user);
        }
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void updateNonId2() {
           User user =new User();
            user.setId(1);
            user.setName("hli");
            user.setEmail("");
            userMapper.updateNon(user);
        }
}
