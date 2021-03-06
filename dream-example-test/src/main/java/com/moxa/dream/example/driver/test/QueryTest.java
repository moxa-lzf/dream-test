package com.moxa.dream.example.driver.test;

import com.moxa.dream.driver.page.Page;
import com.moxa.dream.driver.resource.ResourceUtil;
import com.moxa.dream.driver.session.SqlSession;
import com.moxa.dream.driver.session.SqlSessionFactory;
import com.moxa.dream.driver.session.SqlSessionFactoryBuilder;
import com.moxa.dream.example.driver.mapper.DeptMapper;
import com.moxa.dream.example.driver.mapper.UserMapper;
import com.moxa.dream.example.driver.table.User;
import com.moxa.dream.example.driver.view.MyView;
import com.moxa.dream.example.driver.view.MyViewUser;
import com.moxa.dream.example.driver.view.ViewDept;
import com.moxa.dream.example.driver.view.ViewUser;
import com.moxa.dream.system.mapper.MethodInfo;
import com.moxa.dream.util.reflection.util.NonCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class QueryTest {
    static SqlSessionFactory sqlSessionFactory;
    static long count = 1000000;

    @BeforeAll
    public static void before() {
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(ResourceUtil.getResourceAsStream("config.xml"));
    }

    @Test
    public void selectMap() {
        long l = System.currentTimeMillis();
        MethodInfo methodInfo = new MethodInfo
                .Builder(sqlSessionFactory.getConfiguration())
                .sql("select * from user where id=@$(id)")
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

    @Test
    public void selectBean() {
        long l = System.currentTimeMillis();
        MethodInfo methodInfo = new MethodInfo
                .Builder(sqlSessionFactory.getConfiguration())
                .sql("select * from user where id=@$(id) ")
                .colType(User.class)
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

    @Test
    public void selectField() {
        long l = System.currentTimeMillis();
        MethodInfo methodInfo = new MethodInfo
                .Builder(sqlSessionFactory.getConfiguration())
                .sql("select user.id from user where id=@$(id) ")
                .build();
        Object value = null;
        for (int i = 0; i < count; i++) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                User user = new User();
                user.setId(1);
                value = sqlSession.execute(methodInfo, user);
            }
        }
        System.out.println((System.currentTimeMillis() - l) + "\t\t" + value);
    }

    @Test
    public void selectTableList() {
        long l = System.currentTimeMillis();
        MethodInfo methodInfo = new MethodInfo
                .Builder(sqlSessionFactory.getConfiguration())
                .sql("select user.id,user.name,dept.id,dept.name from user left join user_dept on user.id=user_dept.user_id left join dept on dept.id=user_dept.dept_id ")
                .colType(ViewUser.class)
                .build();
        Object value = null;
        for (int i = 0; i < count; i++) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                value = sqlSession.execute(methodInfo, null);
            }
        }
        System.out.println((System.currentTimeMillis() - l) + "\t\t" + value);
    }
    @Test
    public void selectAll() {
        long l = System.currentTimeMillis();
        MethodInfo methodInfo = new MethodInfo
                .Builder(sqlSessionFactory.getConfiguration())
                    .sql("select @all(),user.id  from user left join user_dept on user.id=user_dept.user_id left join dept on dept_id=dept.id")
                .colType(ViewUser.class)
                .rowType(List.class)
                .build();
        Object value = null;
        for (int i = 0; i < count/count; i++) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                value = sqlSession.execute(methodInfo, null);
            }
        }
        System.out.println((System.currentTimeMillis() - l) + "\t\t" + value);
    }
    @Test
    public void selectMyView() {
        long l = System.currentTimeMillis();
        MethodInfo methodInfo = new MethodInfo
                .Builder(sqlSessionFactory.getConfiguration())
                .sql("select * from user where id=@$(id) ")
                .colType(MyView.class)
                .build();
        Object value = null;
        for (int i = 0; i < count; i++) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                value = sqlSession.execute(methodInfo, 1);
            }
        }
        System.out.println((System.currentTimeMillis() - l) + "\t\t" + value);
    }
    @Test
    public void selectTable() {
        long l = System.currentTimeMillis();
        MethodInfo methodInfo = new MethodInfo
                .Builder(sqlSessionFactory.getConfiguration())
                .sql("select @all(user,dept) from @table(user,dept,user_dept) ")
                .colType(Map.class)
                .build();
        Object value = null;
        for (int i = 0; i < count; i++) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                value = sqlSession.execute(methodInfo, 1);
            }
        }
        System.out.println((System.currentTimeMillis() - l) + "\t\t" + value);
    }
    @Test
    public void userMapper$SelectUserById() {
        long l = System.currentTimeMillis();
        Object value = null;
        for (int i = 0; i < count; i++) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                MyViewUser myViewUser = userMapper.selectUserById(1);
            }
        }
        System.out.println((System.currentTimeMillis() - l));
    }
    @Test
    public void userMapper$SelectUserList() {
        long l = System.currentTimeMillis();
        Object value = null;
        for (int i = 0; i < count; i++) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                List<MyViewUser> myViewUsers = userMapper.selectUserList();
            }
        }
        System.out.println((System.currentTimeMillis() - l));
    }
    @Test
    public void deptMapper$selectDeptByUserId() {
        long l = System.currentTimeMillis();
        Object value = null;
        for (int i = 0; i < count; i++) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
                List<ViewDept> viewDepts = deptMapper.selectDeptByUserId(1);
            }
        }
        System.out.println((System.currentTimeMillis() - l));
    }
    @Test
    public void userMapper$selectUserWithDeptById() {
        long l = System.currentTimeMillis();
        Object value = null;
        for (int i = 0; i < count; i++) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                MyViewUser myViewUser = userMapper.selectUserWithDeptById(1);
                value=myViewUser;
            }
        }
        System.out.println((System.currentTimeMillis() - l));
    }
    @Test
    public void userMapper$selectPageUserList() {
        long l = System.currentTimeMillis();
        Object value = null;
        for (int i = 0; i < count; i++) {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                List<MyViewUser> page = userMapper.selectPageUserList(new Page(1, 1));
                value=page;
            }
        }
        System.out.println((System.currentTimeMillis() - l));
    }
}
