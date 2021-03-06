package com.moxa.dream.example.driver;

import com.moxa.dream.driver.resource.ResourceUtil;
import com.moxa.dream.system.datasource.DataSourceFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MyDataSourceFactory implements DataSourceFactory {
    private DataSource dataSource;

    @Override
    public void setProperties(Properties properties) {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
        config.addDataSourceProperty("URL", "jdbc:h2:mem:db");
        config.addDataSourceProperty("user", "sa");
        config.addDataSourceProperty("password", "");
        dataSource = new HikariDataSource(config);
        try {
            runScript();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void runScript() throws SQLException, IOException {
        InputStream resourceAsStream = ResourceUtil.getResourceAsStream("sample.sql");
        byte[] bytes=new byte[resourceAsStream.available()];
        resourceAsStream.read(bytes);
        String[] sqlList = new String(bytes).split(";");
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        for (String sql : sqlList) {
            statement.execute(sql);
        }
        statement.close();
        connection.close();
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}