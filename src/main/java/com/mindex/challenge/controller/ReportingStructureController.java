package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.data.Views;
import com.mindex.challenge.service.ReportingStructureService;

/**
 * Controller for ReportingStructure endpoints
 */
@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    /**
     * Calculate and return the reporting structure for a given employee id.
     * 
     * @param employeeId The id of the Employee being retrieved.
     * @return The ReportingStructure for the Employee including full Employee
     *         object a full directReports tree of Employees.
     */
    @GetMapping("/reporting-structure/employee/{employeeId}")
    @JsonView(Views.Full.class)
    public ReportingStructure read(@PathVariable String employeeId) {
        LOG.debug("Received employee for get Reporting Structre Request with employeeId [{}]", employeeId);

        return reportingStructureService.read(employeeId);
    }
}
