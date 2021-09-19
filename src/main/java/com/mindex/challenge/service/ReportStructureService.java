package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

/**
 * @author Zach <zach@findzach.com>
 * @since 9/15/2021
 */
public interface ReportStructureService {

    /**
     * Formulates a ReportStructure based off the employeeId
     * @param employeeId - The UUID of the Employee
     * @return - The ReportingStructure POJO, or returns null if employee doesn't exist with employeeId
     */
    ReportingStructure retrieve(String employeeId);

    /**
     * A method that calculates the total reports
     * @param employee - The Employee
     * @return - The total amount of people that are in your ReportStructure
     */
    int calculateTotalReports(Employee employee);

}
