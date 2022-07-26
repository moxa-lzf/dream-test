package com.moxa.dream.boot.test;

import com.moxa.dream.boot.BootApplication;
import com.moxa.dream.boot.mapper.UserMapper;
import com.moxa.dream.boot.service.UserService;
import com.moxa.dream.boot.table.User;
import com.moxa.dream.boot.template.mapper.TemplateMapper;
import com.moxa.dream.boot.view.UserView;
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
    private UserMapper userMapper;
    @Autowired
    private TemplateMapper templateMapper;
    static int count = 1;
    @Test
    public void test() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            User user = userMapper.findByName("Jone");
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void test2() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            User user = userMapper.findByName2("'Jone'");
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void test3() {
        List<User> userList = userMapper.findAll();
        userList.forEach(System.out::println);
    }
    @Test
    public void test4() {
        Object v = userMapper.findByAll();
        System.out.println(v);
    }
    @Test
    public void test5() {
        List<UserView> userViews = userMapper.selectAll();
        userViews.forEach(System.out::println);
    }
    @Test
    public void testPage() {
        Page page=new Page(1,1);
       List<User>userList = userMapper.findByPage(page);
        page.setRows(userList);
        System.out.println(page.getTotal());
    }
    @Test
    public void testSelectById() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            templateMapper.selectById(User.class, 1);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void testSelectByIds() {
        long l = System.currentTimeMillis();
        List<User> users=null;
        for (int i = 0; i < count; i++) {
            users = templateMapper.selectByIds(User.class, Arrays.asList(1, 2, 3, 4, 5, 6));
        }
        System.out.println(System.currentTimeMillis() - l);
        users.forEach(System.out::println);
    }
    @Test
    public void existById(){
        long l = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            boolean b = templateMapper.existById(User.class, 12);
        }
        System.out.println(System.currentTimeMillis() - l);
    }

}
