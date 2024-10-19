package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String reportingUrl;

    @Autowired
    private ReportingStructureService reportingService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingUrl = "http://localhost:" + port + "/reporting-structure/employee/{id}";
    }

    @Test
    public void testRead() {
        // Get top level employee (4 reports)
        ReportingStructure topReportingStructure = restTemplate
                .getForEntity(reportingUrl, ReportingStructure.class, "16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();
        assertEquals(topReportingStructure.getNumberOfReports(), 4);
        assertEquals(topReportingStructure.getEmployee().getEmployeeId(), "16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertTrue(topReportingStructure.getEmployee().getDirectReports().stream()
                .anyMatch(report -> "b7839309-3348-463b-a7e3-5de1c168beb3".equals(report.getEmployeeId())));

        // Get bottom level employee (0 reports)
        ReportingStructure bottomReportingStructure = restTemplate
                .getForEntity(reportingUrl, ReportingStructure.class, "62c1084e-6e34-4630-93fd-9153afb65309").getBody();
        assertEquals(bottomReportingStructure.getNumberOfReports(), 0);
        assertEquals(bottomReportingStructure.getEmployee().getEmployeeId(), "62c1084e-6e34-4630-93fd-9153afb65309");
        assertEquals(bottomReportingStructure.getEmployee().getDirectReports().size(), 0);

        // Get employee who does not exist
        HttpStatusCode errorCode = restTemplate
                .getForEntity(reportingUrl, ReportingStructure.class, "00000000-0000-0000-0000-000000000000")
                .getStatusCode();
        assertEquals(errorCode.value(), 500);
    }
}
