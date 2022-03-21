package com.moxa.dream.example.antlr.myfucntion.override;


import com.moxa.dream.example.antlr.AbstractSqlTest;

public class OverrideTest extends AbstractSqlTest {
    public static void main(String[] args) {
        OverrideTest overrideTest = new OverrideTest();
        overrideTest.testSqlForMany("select isnull(a) from dual", null, null);
        System.out.println("\n*********use override***********");
        overrideTest.testSqlForMany("select isnull(a,b) from dual", new MyOverrideFunctionFactory(), null);

    }

}
