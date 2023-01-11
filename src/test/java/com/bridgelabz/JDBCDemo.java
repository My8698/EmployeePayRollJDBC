package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCDemo {

    @Test
    public void getDB_Connection() {
        Connection dbConnection = new JDBCConnection().getDBConnection();
        System.out.println(dbConnection);
    }
    @Test
    public void givenEmployeePayrollInDb_WhenRetrieved_ShouldMatchEmployeeCount() throws SQLException {
        EmployeePayRollService employeePayRollService = new EmployeePayRollService();
        List<EmployeePayRollData> employeePayrollData = employeePayRollService.readEmployeePayrollData(EmployeePayRollService.IOService.DB_IO);
        Assert.assertEquals(4,employeePayrollData.size());
    }
}
