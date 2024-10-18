package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the ReportingStructureService interface.
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Calculate and return the reporting structure for a given employee id.
     * 
     * @param employeeId The id of the Employee being retrieved.
     * @return The ReportingStructure for the Employee including full Employee
     *         object a full directReports tree of Employees.
     */
    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("Looking up ReportingStructure for Employee with employeeId [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        ReportingStructure employeeReportingStructure = new ReportingStructure();
        employeeReportingStructure.setEmployee(employee);

        // A simple web search revealed most companies have less than 10 as a
        // max depth, 30 should cover majority of cases and prevent overflows.
        employeeReportingStructure.setNumberOfReports(getReportCount(employee, 30));
        return employeeReportingStructure;
    }

    /**
     * Recursively counts an Employee's direct reports.
     * 
     * @param employee The employee whose direct reports need to be counted.
     * @param maxDepth The maximum number of steps to search before giving up.
     *                 Prevents infinite recursion and overflows.
     * @return A count of all the direct reports for the given employee,
     *         including all direct reports below them.
     */
    private int getReportCount(Employee employee, int maxDepth) {
        int count = 0;
        if (maxDepth > 0) {
            List<Employee> reports = employee.getDirectReports();
            if (reports != null) {
                for (Employee report : employee.getDirectReports()) {
                    count += 1 + getReportCount(report, maxDepth - 1);
                }
            }
        }
        return count;
    }

}
