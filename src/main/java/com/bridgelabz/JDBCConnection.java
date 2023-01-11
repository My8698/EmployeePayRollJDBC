/**package com.bridgelabz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private String url = "jdbc:mysql://localhost:3306/emppayrollservice?useSSL=false";
    private String username = "root";
    private String password = "M@dhuri3";
    Connection connection;

    public Connection getDBConnection() {

        try {
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Connected!");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured : " +e);
        }
        return connection;
    }
}**/
