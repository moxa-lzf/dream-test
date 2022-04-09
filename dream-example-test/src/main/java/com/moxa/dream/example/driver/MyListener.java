package com.moxa.dream.example.driver;

import com.moxa.dream.system.core.listener.DeleteListener;
import com.moxa.dream.system.core.listener.InsertListener;
import com.moxa.dream.system.core.listener.QueryListener;
import com.moxa.dream.system.core.listener.UpdateListener;
import com.moxa.dream.system.mapped.MappedStatement;

public class MyListener implements QueryListener, InsertListener, UpdateListener, DeleteListener {
    @Override
    public void before(MappedStatement mappedStatement) {
    }

    @Override
    public void afterReturn(Object result, MappedStatement mappedStatement) {

    }

    @Override
    public void exception(Exception e, MappedStatement mappedStatement) {

    }
}
