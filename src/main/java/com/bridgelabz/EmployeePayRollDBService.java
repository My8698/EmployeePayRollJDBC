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
    public ResultSet retrieveAccordingToDate(String date_string) {
        String sqlQuery = String.format("select * from employee_payroll where start BETWEEN CAST('%s' AS DATE) AND DATE(NOW());",date_string);
        try(Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println("\nData of employee's who joined between "+date_string+" and now are as follows\n");
            while (resultSet.next())
            {
                System.out.println(resultSet.getString(1)+"	"+
                        resultSet.getString(2)+"	"+
                        resultSet.getString(3)+"	"+
                        resultSet.getString(4)+"	"+
                        resultSet.getString(5)+"	"+
                        resultSet.getString(6)+"	"+
                        resultSet.getString(7));
            }
            System.out.println("\nEnd of data\n");
        }
        catch (SQLException e)
        {
            System.out.println("Exception occured while executing query on date : "+e );
        }
        return null;
    }
    public ResultSet sumUsingGroupByGender(String value) {
        String sqlQuery = String.format("Select gender,sum(%s) from employee_payroll group by gender;",value);
        try(Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println("\nSum of Salary of the employee's group by Gender is as follows \n");
            while (resultSet.next())
            {
                System.out.println(resultSet.getString(1)+" "+
                        resultSet.getString(2));
            }
            System.out.println("\nEnd of data\n");
        }
        catch (SQLException e)
        {
            System.out.println("Exception occured while executing sumUsingGroupByGender : "+e );
        }
        return null;
    }
    public ResultSet avgUsingGroupByGender(String value) {
        String sqlQuery = String.format("Select gender,avg(%s) from employee_payroll group by gender;",value);
        try(Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println("\nAverage of Salary of the employee's group by Gender is as follows \n");
            while (resultSet.next())
            {
                System.out.println(resultSet.getString(1)+" "+
                        resultSet.getString(2));
            }
            System.out.println("\nEnd of data\n");
        }
        catch (SQLException e)
        {
            System.out.println("Exception occured while executing avgUsingGroupByGender : "+e );
        }
        return null;
    }
    public ResultSet minSalaryUsingGroupByGender(String value) {
        String sqlQuery = String.format("Select gender,min(%s) from employee_payroll group by gender;",value);
        try(Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println("\nMin Salary of the employee grouped by Gender is : \n");
            while (resultSet.next())
            {
                System.out.println(resultSet.getString(1)+" "+
                        resultSet.getString(2));
            }
            System.out.println("\nEnd of data\n");
        }
        catch (SQLException e)
        {
            System.out.println("Exception occured while executing minSalaryUsingGroupByGender : "+e );
        }
        return null;
    }
    public ResultSet maxSalaryUsingGroupByGender(String value) {
        String sqlQuery = String.format("Select gender,max(%s) from employee_payroll group by gender;",value);
        try(Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println("\nMax Salary of the employee grouped by Gender is : \n");
            while (resultSet.next())
            {
                System.out.println(resultSet.getString(1)+" "+
                        resultSet.getString(2));
            }
            System.out.println("\nEnd of data\n");
        }
        catch (SQLException e)
        {
            System.out.println("Exception occured while executing minSalaryUsingGroupByGender : "+e );
        }
        return null;
    }
    public ResultSet countEmployeesUsingGroupByGender(String value) {
        String sqlQuery = String.format("Select gender,count(%s) from employee_payroll group by gender;",value);
        try(Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println("\nNumber of employees grouped by Gender are : \n");
            while (resultSet.next())
            {
                System.out.println(resultSet.getString(1)+" "+
                        resultSet.getString(2));
            }
            System.out.println("\nEnd of data\n");
        }
        catch (SQLException e)
        {
            System.out.println("Exception occured while executing minSalaryUsingGroupByGender : "+e );
        }
        return null;
    }
}
