package com.java;

import java.sql.*;

public class H2Example {
    public static void main(String[] args) {

        // 定义数据库连接URL
        String url = "jdbc:h2:~/test"; // 在用户主目录下创建名为test的数据库

        try {
            // 创建数据库连接
            Connection connection = DriverManager.getConnection(url);
            // 创建执行 SQL 并返回结果的对象
            Statement statement = connection.createStatement();

            /*
             创建表
             */
            String createTableSQL = "drop table if exists city;" + "create table city (id int primary key auto_increment, name varchar, state varchar, country varchar);\n";

            statement.executeUpdate(createTableSQL);

            /*
             插入表数据
             */
            String insertTableSQL = "insert into city (name, state, country) values ('San Francisco', 'CA', 'US');" + "insert into city (name, state, country) values ('XiAn', 'XC', 'China');" + "insert into city (name, state, country) values ('Beijing', 'EX', 'China');" + "insert into city (name, state, country) values ('ZhengZhou', 'US', 'China');";

            statement.executeUpdate(insertTableSQL);

            /*
            查询数据
             */
            String selectTableSQL = "select * from city";

            PreparedStatement preparedStatement = connection.prepareStatement(selectTableSQL);
            ResultSet rs = preparedStatement.executeQuery();

            /*
            打印数据
             */
            ResultSetPrinter.printResultSet(rs);

            // 关闭数据库连接
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
