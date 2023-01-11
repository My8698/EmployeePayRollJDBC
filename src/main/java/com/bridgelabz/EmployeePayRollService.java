package com.bridgelabz;
import java.util.List;
public class EmployeePayRollService {
    public enum IOService {
        CONSOLE_IO,FILE_IO,DB_IO,RSET_IO;
    }
    public List<EmployeePayRollData> employeePayrollList;
    public EmployeePayRollService() {}
    public EmployeePayRollService (List<EmployeePayRollData> employeePayrollList) {
        this.employeePayrollList=employeePayrollList;
    }
    public List<EmployeePayRollData> readEmployeePayrollData(IOService ioService) {
        if(ioService.equals(IOService.DB_IO))
            this.employeePayrollList = new EmployeePayRollDBService().readData();
        return this.employeePayrollList;
    }
}