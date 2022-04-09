package com.moxa.dream.example.driver.test;

import com.moxa.dream.driver.session.SqlSession;
import com.moxa.dream.driver.session.SqlSessionFactory;
import com.moxa.dream.driver.session.SqlSessionFactoryBuilder;
import com.moxa.dream.example.driver.table.User;
import com.moxa.dream.system.mapper.MethodInfo;
import com.moxa.dream.util.reflection.util.NonCollection;
import com.moxa.dream.util.resource.ResourceUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InsertTest {
    static SqlSessionFactory sqlSessionFactory;
    static long count = 1000000;

    @BeforeAll
    public static void before() {
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(ResourceUtil.getResourceAsStream("config.xml"));
    }

    @Test
    public void insertSimple() {
        long l = System.currentTimeMillis();
        MethodInfo methodInfo = new MethodInfo
                .Builder(sqlSessionFactory.getConfiguration())
                .sql("insert into user(name)values(@$(name))")
                .colType(Object.class)
                .rowType(NonCollection.class)
                .generatedKeys(true)
                .build();
        User user=new User();
        Object value;
        for (int i = 0; i < count; i++) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                user.setName("user"+i);
                value = sqlSession.execute(methodInfo, user);
            }
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void insertBatch() {
        long l = System.currentTimeMillis();
        MethodInfo methodInfo = new MethodInfo
                .Builder(sqlSessionFactory.getConfiguration())
                .sql("insert into user(id,name)values(@$(id),@$(name))")
                .colType(Object.class)
                .rowType(NonCollection.class)
                .build();
            try (SqlSession sqlSession = sqlSessionFactory.openSession(false,true,false)) {
                for (int i = 0; i < count; i++) {
                    User user = new User();
                user.setId(100+i);
                user.setName("user"+i);
                Object value = sqlSession.execute(methodInfo, user);
            }
        }
        System.out.println(System.currentTimeMillis() - l);
    }
    @Test
    public void insertMany() {
        long l = System.currentTimeMillis();
        MethodInfo methodInfo = new MethodInfo
                .Builder(sqlSessionFactory.getConfiguration())
                .sql("insert into user(name)values @foreach(list,(@$(item.name)))")
                .colType(Object.class)
                .rowType(NonCollection.class)
                .build();
        for (int i = 0; i < count/100; i++) {
            List<String> nameList=new ArrayList<>();
            for(int k=0;k<100;k++){
                nameList.add("name"+i);
            }
            Map<String,Object> map=new HashMap<>();
            map.put("list",nameList);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                Object value = sqlSession.execute(methodInfo, map);
            }
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
