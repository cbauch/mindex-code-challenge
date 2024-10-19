package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * Interface for retrieving Compensation information for Employees
 */
public interface CompensationService {

    /**
     * Create a new compensation for an Employee.
     * 
     * @param employeeId   The id for the employee this compensation is for.
     * @param compensation The new compensation to create.
     * @return The newly created
     */
    Compensation create(String employeeId, Compensation compensation);

    /**
     * Return the compensation for a given employee id.
     * 
     * @param employeeId The id of the Employee whose Compensation is being
     *                   retrieved.
     * @return The Compensation for the Employee.
     */
    Compensation read(String employeeId);
}
