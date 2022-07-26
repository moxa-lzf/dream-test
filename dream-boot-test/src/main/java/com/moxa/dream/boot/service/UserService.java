package com.moxa.dream.boot.service;

import com.moxa.dream.boot.mapper.UserMapper;
import com.moxa.dream.boot.spring.annotation.SessionControl;
import com.moxa.dream.boot.table.User;
import com.moxa.dream.boot.template.mapper.TemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TemplateMapper templateMapper;

    public User findByName(String state) {
        User user = userMapper.findByName(state);
        return user;
    }

    public User findByName2(String state) {
        User user = userMapper.findByName2(state);
        return user;
    }
    public Integer update(User user) {
        return userMapper.update(user);
    }

    @Transactional
    @SessionControl(cache = false,batch = true)
    public void insertBatch(List<User> userList) {
        templateMapper.insertBatch(userList);
    }
    @Transactional
    @SessionControl(cache = false,batch = true)
    public void insertBatch2(List<User> userList) {
        for (User user : userList) {
            userMapper.insert(user);
        }
    }
}
