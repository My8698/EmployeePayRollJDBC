package com.bridgelabz;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class EmployeePayRollDBService {
    private String url = "jdbc:mysql://localhost:3306/EmpPayRollService?useSSL=false";
    private String username = "root";
    private String password = "M@dhuri3";
    Connection connection;

    public List<EmployeePayRollData> readData() {
        String sqlQuery = "Select * from employee_payroll";
        List<EmployeePayRollData> employeePayrollList = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("basic_pay");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayRollData(id,name,salary,startDate));
            }
            connection.close();
        }catch (Exception e) {
            System.out.println(e);
        }
        return employeePayrollList;
    }

    private Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Connected to Database ");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured : " +e);
        }
        return connection;
    }
    public int updateEmployeeDataUsingStatement(String name, double basic_pay) {
        String sqlQuery = String.format("update employee_payroll set basic_pay = %2f where name = '%s';",basic_pay, name);
        try(Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sqlQuery);
        }catch (Exception e) {
            System.out.println("Exception occured : "+e);
        }
        return 0;
    }
}
