package com.moxa.dream.example.antlr.invoker;

import com.moxa.dream.antlr.config.Assist;
import com.moxa.dream.antlr.exception.InvokerException;
import com.moxa.dream.antlr.factory.AntlrInvokerFactory;
import com.moxa.dream.antlr.factory.InvokerFactory;
import com.moxa.dream.antlr.invoker.$Invoker;
import com.moxa.dream.antlr.smt.PackageStatement;
import com.moxa.dream.antlr.sql.ToMYSQL;
import com.moxa.dream.antlr.sql.ToORACLE;
import com.moxa.dream.example.antlr.AbstractSqlTest;
import com.moxa.dream.util.common.ObjectWrapper;

import java.util.*;

public class ParamInvokerTest extends AbstractSqlTest {
    private List<InvokerFactory> invokerFactoryList = new ArrayList<>();

    public ParamInvokerTest() {
        AntlrInvokerFactory antlrInvokerFactory = new AntlrInvokerFactory();
        invokerFactoryList.add(antlrInvokerFactory);
    }


    public static void main(String[] args) {
        ParamInvokerTest paramInvokerTest = new ParamInvokerTest();
        paramInvokerTest.testRepInvoker();
        paramInvokerTest.test$Invoker();
        paramInvokerTest.testNonInvoker();
        paramInvokerTest.testSimpleForEachInvoker();
        paramInvokerTest.testMapForEachInvoker();
        paramInvokerTest.testforEachInsert();
    }

    public void testRepInvoker() {
        PackageStatement packageStatement = createStatement("select 1 from dual where a=@rep(a) and b=@rep(b) or c=@rep(c)", null);
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("a", null);
            paramMap.put("b", "'test_b'");
            paramMap.put("c", "'test_c'");
            Map<Class, Object> map = new HashMap<>();
            map.put(ObjectWrapper.class, ObjectWrapper.wrapper(paramMap));
            System.out.println(new ToMYSQL().toStr(packageStatement, new Assist(invokerFactoryList, map),null));
        } catch (InvokerException e) {
            throw new RuntimeException(e);
        }
    }

    public void test$Invoker() {
        PackageStatement packageStatement = createStatement("select 1 from dual where a=@$(a) and b=@$(b) or c=@$(c)", null);
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("a", null);
            paramMap.put("b", "'test_b'");
            paramMap.put("c", "'test_c'");
            Map<Class, Object> map = new HashMap<>();
            map.put(ObjectWrapper.class, ObjectWrapper.wrapper(paramMap));
            System.out.println(new ToMYSQL().toStr(packageStatement, new Assist(invokerFactoryList, map),null));
        } catch (InvokerException e) {
            throw new RuntimeException(e);
        }
    }

    public void testNonInvoker() {
        PackageStatement packageStatement = createStatement("select year from dual where @non(date between @$(startDate) and @$(endDate))", null);
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("endDate", "2020-12-12");
            Map<Class,Object>map=new HashMap<>();
            map.put(ObjectWrapper.class,ObjectWrapper.wrapper(paramMap));
            System.out.println(new ToMYSQL().toStr(packageStatement, new Assist(invokerFactoryList, map),null));

        } catch (InvokerException e) {
            throw new RuntimeException(e);
        }
    }

    public void testSimpleForEachInvoker() {
        PackageStatement packageStatement = createStatement("select 1 from dual where a in(@foreach(a))", null);
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("a", Arrays.asList("a", "b", "c", "d", "e"));
            Map<Class,Object>map=new HashMap<>();
            map.put(ObjectWrapper.class,ObjectWrapper.wrapper(paramMap));
            System.out.println(new ToMYSQL().toStr(packageStatement, new Assist(invokerFactoryList, map),null));
        } catch (InvokerException e) {
            throw new RuntimeException(e);
        }
    }


    public void testMapForEachInvoker() {
        PackageStatement packageStatement = createStatement("select 1 from dual where a in(@foreach(a,@$(item.name)))", null);
        try {
            Map<String, Object> paramMap = new HashMap<>();
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Map map = new HashMap();
                map.put("name", "name" + i);
                list.add(map);
            }
            paramMap.put("a", list);
            Map<Class, Object> map = new HashMap<>();
            map.put(ObjectWrapper.class, ObjectWrapper.wrapper(paramMap));
            System.out.println(new ToMYSQL().toStr(packageStatement, new Assist(invokerFactoryList, map),null));
        } catch (InvokerException e) {
            throw new RuntimeException(e);
        }
    }

    public void testforEachInsert() {
        PackageStatement packageStatement = createStatement("insert into dual(id,name)values @foreach(list,(@$(item.id),@$(item.name)))", null);
        try {
            Map<String, Object> paramMap = new HashMap<>();
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Map map = new HashMap();
                map.put("name", "name" + i);
                map.put("id", i);
                list.add(map);
            }
            paramMap.put("list", list);
            Map<Class, Object> map = new HashMap<>();
            map.put(ObjectWrapper.class, ObjectWrapper.wrapper(paramMap));
            System.out.println(new ToMYSQL().toStr(packageStatement, new Assist(invokerFactoryList, map),null));
        } catch (InvokerException e) {
            throw new RuntimeException(e);
        }
    }
}
