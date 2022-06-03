package com.moxa.dream.boot;

import com.moxa.dream.antlr.sql.ToMYSQL;
import com.moxa.dream.boot.autoconfigure.SqlSessionFactoryBean;
import com.moxa.dream.driver.config.DefaultConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.util.Arrays;

@SpringBootApplication
public class BootApplication {
    public static void main(String[] args) {
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor;
        SpringApplication.run(BootApplication.class, args);
    }

    //    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        FactoryBean factoryBean;
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(null, ToMYSQL.class.getName(), null, null);
        DefaultConfig defaultConfig = sqlSessionFactoryBean.getDefaultConfig();
        defaultConfig.setTablePackages(Arrays.asList("com.moxa.dream.boot"));
        defaultConfig.setMapperPackages(Arrays.asList("com.moxa.dream.boot"));
        defaultConfig.setDialect(ToMYSQL.class.getName());
        return sqlSessionFactoryBean;
    }
}
