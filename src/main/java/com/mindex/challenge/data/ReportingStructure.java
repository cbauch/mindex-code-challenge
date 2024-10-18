package com.mindex.challenge.data;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * ReportingStructure is a meta object calculated by traversing an Employee's
 * direct reports tree. Includes basic getters and setters for the employee and
 * numberOfReports fields.
 */
@Document
public class ReportingStructure {

    // The employee with their direct reports tree filled out.
    @JsonView(Views.Full.class)
    private Employee employee;

    // The total number of a reports the employee has recursively counted
    // by traversing the direct reports tree.
    @JsonView(Views.Summary.class)
    private int numberOfReports;

    public ReportingStructure() {

    }

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
