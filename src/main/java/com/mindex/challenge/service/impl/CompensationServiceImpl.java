package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Create a new compensation for an Employee.
     * 
     * @param compensation The new compensation to create.
     * @return The newly created Compensation.
     */
    public Compensation create(String employeeId, Compensation compensation) {
        LOG.debug("Creating compensation for employeeId [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee.getCompensation() != null) {
            // More secure than telling someone they guessed a real employee id
            throw new RuntimeException("Invalid Compensation employeeId: " + employeeId);
        }

        employee.setCompensation(compensation);
        employeeRepository.save(employee);

        return compensation;
    }

    /**
     * Return the compensation for a given employee id.
     * 
     * @param employeeId The id of the Employee whose Compensation is being
     *                   retrieved.
     * @return The Compensation for the Employee.
     */
    public Compensation read(String employeeId) {
        LOG.debug("Looking for compensation with employee id [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        Compensation compensation = employee.getCompensation();

        if (compensation == null) {
            throw new RuntimeException("Invalid Compensation employeeId: " + employeeId);
        }

        return compensation;
    }
}
