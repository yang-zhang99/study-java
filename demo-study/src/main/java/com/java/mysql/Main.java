//package test.org.mysql;
//
//import com.mysql.jdbc.MySQLConnection;
//import com.mysql.jdbc.StatementImpl;
//
//import java.sql.*;
//import java.util.Properties;
//
//public class Main {
//
//    public static void main(String[] args) throws SQLException {
//        Driver driver = new com.mysql.jdbc.Driver();
//        String url = "jdbc:mysql://192.168.4.120:3306/zx-user";
//        Properties properties = new Properties();
//
//        properties.setProperty("user", "root");
//        properties.setProperty("password", "123456");
//        Connection connection = driver.connect(url, properties);
//
//        String sql = "SELECT * FROM user limit 10";
//
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        Statement statement = connection.createStatement();
//
//        ResultSet rs = preparedStatement.executeQuery();
//
//        int id;//声明3个变量分别为id，age,sex
//        String auName, phone;//声明2个变量分别为用户名，密码
//        System.out.println("id\t 用户名\t 电话 \t ");//其中\t相当于8个空格
//        while (rs.next()) {//遍历结果集
//            id = rs.getInt("id");//获得id
//            auName = rs.getString("au_name");//
//            phone = rs.getString(5);//
//            System.out.println(id + "\t" + auName + "\t" + phone + "\t");
//        }
//
//        rs.close();
//        connection.close();
//        preparedStatement.close();
//    }
//
//}
