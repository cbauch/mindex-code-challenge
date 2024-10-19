package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationCreateUrl;
    private String compensationLookupUrl;

    @Autowired
    private CompensationService compensationService;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationCreateUrl = "http://localhost:" + port + "/compensation/employee/{id}";
        compensationLookupUrl = "http://localhost:" + port + "/compensation/employee/{id}";
    }

    @Test
    public void testCreateRead() {
        Compensation testCompensation = new Compensation();
        Date today = new Date();
        testCompensation.setEffectiveDate(today);
        testCompensation.setSalary(150000);

        // Create checks
        // Create a compensation for an Employee
        Compensation createdCompensation = restTemplate.postForEntity(
                compensationCreateUrl, testCompensation, Compensation.class,
                "16a596ae-edd3-4847-99fe-c4518e82c86f")
                .getBody();

        assertNotNull(createdCompensation.getSalary());

        // Try to create a second compensation for an Employee
        HttpStatusCode errorCode = restTemplate.postForEntity(
                compensationCreateUrl, testCompensation, Compensation.class,
                "16a596ae-edd3-4847-99fe-c4518e82c86f")
                .getStatusCode();

        assertEquals(errorCode.value(), 500);

        // Try to create a compensation for an Employee that does not exist
        errorCode = restTemplate.postForEntity(
                compensationCreateUrl, testCompensation, Compensation.class,
                "00000000-0000-0000-0000-000000000000")
                .getStatusCode();

        assertEquals(errorCode.value(), 500);

        // Read checks
        // Get a Compensation that exists
        Compensation readCompensation = restTemplate
                .getForEntity(
                        compensationLookupUrl, Compensation.class,
                        "16a596ae-edd3-4847-99fe-c4518e82c86f")
                .getBody();
        assertEquals(createdCompensation.getSalary(), readCompensation.getSalary());
        assertEquals(createdCompensation.getEffectiveDate(), readCompensation.getEffectiveDate());

        // Get a Compensation that does not exist
        errorCode = restTemplate
                .getForEntity(
                        compensationLookupUrl, Compensation.class,
                        "62c1084e-6e34-4630-93fd-9153afb65309")
                .getStatusCode();

        assertEquals(errorCode.value(), 500);

        // Get a Compensation for an Employee who does not exist
        errorCode = restTemplate
                .getForEntity(
                        compensationLookupUrl, Compensation.class,
                        "00000000-0000-0000-0000-000000000000")
                .getStatusCode();

        assertEquals(errorCode.value(), 500);

    }
}
