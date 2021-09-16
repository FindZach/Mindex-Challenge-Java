package com.mindex.challenge.data;

import java.util.Date;

/**
 * @author Zach S <zach@findzach.com>
 * @since 9/15/2021
 */
public class Compensation {

    private String id;
    private String employeeId;
    private double salary;
    private Date effectiveDate;

    public Compensation() {
        setEffectiveDate(new Date());
    }

    public String getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

}
