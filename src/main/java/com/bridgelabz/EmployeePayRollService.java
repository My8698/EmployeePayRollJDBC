package com.bridgelabz;
import java.util.List;
public class EmployeePayRollService {
    public enum IOService {
        CONSOLE_IO,FILE_IO,DB_IO,RSET_IO;
    }

    public List<EmployeePayRollData> employeePayrollList;

    public EmployeePayRollService() {}

    public EmployeePayRollService (List<EmployeePayRollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }

    public List<EmployeePayRollData> readEmployeePayrollData(IOService ioService) {
        if(ioService.equals(IOService.DB_IO))
            this.employeePayrollList = new EmployeePayRollDBService().readData();
        return this.employeePayrollList;
    }

    public void updateEmployeeDataUsingStatement(String name, double basic_pay) {
        int result=new EmployeePayRollDBService().updateEmployeeDataUsingStatement(name,basic_pay);
        if(result == 0) return;
        EmployeePayRollData employeePayrollData = this.getEmployeePayrollData(name);
        if(employeePayrollData!=null)
            employeePayrollData.basic_pay = basic_pay;
    }

    private EmployeePayRollData getEmployeePayrollData(String name) {
        return this.employeePayrollList.stream().filter(employeePayrollData -> employeePayrollData.name.equals(name)).findFirst().orElse(null);
    }
}