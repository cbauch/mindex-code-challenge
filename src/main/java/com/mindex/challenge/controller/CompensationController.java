package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Views;
import com.mindex.challenge.service.CompensationService;

/**
 * Controller for Compensation endpoints
 */
@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    /**
     * Creates a new compensation
     * 
     * @param compensation The data for the new compensation.
     * @return The newly created compensation object.
     */
    @PostMapping("/compensation/employee/{employeeId}")
    @JsonView(Views.Confidential.class)
    public Compensation create(@PathVariable String employeeId, @RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request [{}]", compensation);

        return compensationService.create(employeeId, compensation);
    }

    /**
     * Looks up a compensation by an Employee's id
     * 
     * @param employeeId The id of the employee whose compensation is to be
     *                   retrieved.
     * @return The Compensation object that was found.
     */
    @GetMapping("/compensation/employee/{employeeId}")
    @JsonView(Views.Confidential.class)
    public Compensation read(@PathVariable String employeeId) {
        LOG.debug("Received get Compensation Request with employeeId [{}]", employeeId);

        return compensationService.read(employeeId);
    }
}
