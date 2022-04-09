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

public class DeleteTest {
    static SqlSessionFactory sqlSessionFactory;
    static long count = 1000000;

    @BeforeAll
    public static void before() {
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(ResourceUtil.getResourceAsStream("config.xml"));
    }

    @Test
    public void deleteimple() {
        long l = System.currentTimeMillis();
        MethodInfo methodInfo = new MethodInfo
                .Builder(sqlSessionFactory.getConfiguration())
                .sql("delete from user where id=@$(id)")
                .colType(Object.class)
                .rowType(NonCollection.class)
                .build();
        for (int i = 0; i < count; i++) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                User user = new User();
                user.setId(1);
                Object value = sqlSession.execute(methodInfo, user);
            }
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
