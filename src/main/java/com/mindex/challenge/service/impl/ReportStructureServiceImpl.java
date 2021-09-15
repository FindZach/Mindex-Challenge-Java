package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Zach S <zach@findzach.com>
 * @since 9/15/2021
 */
@Service
public class ReportStructureServiceImpl implements ReportStructureService {

    @Autowired
    private EmployeeService employeeService;


    public ReportStructureServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    /**
     * Formulates a ReportStructure based off the employeeId
     *
     * @param employeeId - The UUID of the Employee
     * @return - The ReportingStructure POJO, or returns null if employee doesn't exist with employeeId
     */
    @Override
    public ReportingStructure retrieve(String employeeId) {
        //Verify employee w/employeeId exists
        Optional<Employee> employeeOptional = Optional.ofNullable(employeeService.read(employeeId));
        if (employeeOptional.isPresent()) {
            ReportingStructure reportingStructure = new ReportingStructure();
            //We've already asked our Repo for this Employee, no need to ask EmployeeService twice.
            reportingStructure.setEmployee(employeeOptional.get());

            //Calls a method that calculates the total reports the employee has
            reportingStructure.setNumberOfReports(calculateTotalReports(employeeOptional.get()));
            return reportingStructure;
        } else {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

    }

    @Override
    public int calculateTotalReports(Employee employee) {
        if (employee != null) {
            int directReportsAmount = directReportsExist(employee) ? employee.getDirectReports().size() : 0;
            return directReportsAmount + (directReportsExist(employee) ? employee.getDirectReports().stream().mapToInt(otherEmployee -> getReportsFromEmployeeId(otherEmployee.getEmployeeId())).sum(): 0);
        }
        return -1;
    }

    /**
     * Simple helper method to verify if the employee object has a valid DirectReport list
     * @param employee
     * @return
     */
    private boolean directReportsExist(Employee employee) {
        return employee.getDirectReports() != null;
    }

    /**
     * A helper method to retrieve the Employee and check if it has directReports
     * @return
     */
    private int getReportsFromEmployeeId(String employeeId) {
        Employee employee = employeeService.read(employeeId);

        if (employee != null) {
            return employee.getDirectReports() != null ? employee.getDirectReports().size() : 0;
        } else return 0;
    }
}
