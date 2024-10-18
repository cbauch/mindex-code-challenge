package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

/**
 * Interface for retrieving ReportingStructure information for Employees
 */
public interface ReportingStructureService {

    /**
     * Calculate and return the reporting structure for a given employee id.
     * 
     * @param employeeId The id of the Employee being retrieved.
     * @return The ReportingStructure for the Employee including full Employee
     *         object a full directReports tree of Employees.
     */
    ReportingStructure read(String employeeId);
}
