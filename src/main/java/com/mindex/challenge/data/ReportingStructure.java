package com.mindex.challenge.data;

/**
 * @author Zach S <zach@findzach.com>
 * @since 9/15/2021
 */
public class ReportingStructure {

    //The 'owner' of this type
    private Employee employee;

    //The total number of reports the employee has
    private int numberOfReports;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}
