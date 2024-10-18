package com.mindex.challenge.data;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Employee data object
 * 
 * Notes: I added some annotations to better handle returning a summary version
 * of the Employee (without all their direct reports). This would keep return
 * types less bloated, especially for larger organizations. The directReports
 * could probably be extended in a way to limit the depth as a future feature.
 * One more possible update would be to return the list of directReportIds in
 * the Summary version. This would allow the users to make calls to get
 * direct reports. I also added an Id and DocumentReference to allow the
 * directReports to be loaded and read.
 */
@Document
public class Employee {
    @Id
    @JsonView(Views.Summary.class)
    private String employeeId;

    @JsonView(Views.Summary.class)
    private String firstName;

    @JsonView(Views.Summary.class)
    private String lastName;

    @JsonView(Views.Summary.class)
    private String position;

    @JsonView(Views.Summary.class)
    private String department;

    @DocumentReference(lazy = true)
    @JsonView(Views.Full.class)
    private List<Employee> directReports;

    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }
}
