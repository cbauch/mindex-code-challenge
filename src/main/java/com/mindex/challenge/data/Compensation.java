package com.mindex.challenge.data;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * The compensation info for an Employee
 */
@Document
public class Compensation {

    private int salary;

    private Date effectiveDate;

    public Compensation() {

    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
