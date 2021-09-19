package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zach S <zach@findzach.com>
 * @since 9/15/2021
 */
@Service
public class ReportStructureServiceImpl implements ReportStructureService {

    private EmployeeService employeeService;

    @Autowired
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
        Employee employee = employeeService.findById(employeeId);

        ReportingStructure reportingStructure = new ReportingStructure();

        reportingStructure.setEmployee(employee);

        //Call the method that calculates the total the employee has
        reportingStructure.setNumberOfReports(calculateTotalReports(employee));
        return reportingStructure;
    }

    /**
     * Calculates the employees DirectReports
     * @param employee - The Employee
     * @return The total number of DirectReports the Employee has
     */
    @Override
    public int calculateTotalReports(Employee employee) {
        if (employee != null) {
            //Get size of direct Employee
            int directReportsAmount = directReportsExist(employee) ? employee.getDirectReports().size() : 0;

            //Combine size of Direct Employee + The Employees Direct Reports
            return directReportsAmount + (directReportsExist(employee) ?
                    employee.getDirectReports().stream().mapToInt(otherEmployee -> getReportsFromEmployeeId(otherEmployee.getEmployeeId())).sum() : 0);
        }
        return -1;
    }

    /**
     * A helper method to verify if the employee object has a valid DirectReport list
     *
     * @param employee The Employee in Question
     * @return Returns true if Employee has directReports, false if not
     */
    private boolean directReportsExist(Employee employee) {
        return employee.getDirectReports() != null;
    }

    /**
     * A helper method to retrieve the Employee and check if it has directReports
     *
     * @return Returns the total Reports from the Employee
     */
    private int getReportsFromEmployeeId(String employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if (employee != null) {
            int count = 0;

            if (employee.getDirectReports() != null) {

                for (Employee directReports : employee.getDirectReports()) {
                    Employee directReportEmployee = employeeService.findById(directReports.getEmployeeId());
                    if (directReportEmployee.getDirectReports() != null) {
                        count += directReportEmployee.getDirectReports().size();
                        count += directReportEmployee.getDirectReports().stream().mapToInt(otherEmployee -> getReportsFromEmployeeId(otherEmployee.getEmployeeId())).sum();
                    }

                }
                count += employee.getDirectReports().size();
            }

            return count;
        } else return 0;
    }
}
