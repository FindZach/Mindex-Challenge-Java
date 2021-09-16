package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Zach S <zach@findzach.com>
 * @since 9/15/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportStructureServiceImplTest {

    private String reportStructureUrl;

    @Autowired
    private ReportStructureService reportStructureService;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportStructureUrl = "http://localhost:" + port + "/employee/{employeeId}/reportstructure";
    }

    @Test
    public void testReportStructureGeneration() {
        //John Lennon employeeId
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";

        ReportingStructure reportingStructure = restTemplate.getForEntity(reportStructureUrl, ReportingStructure.class, employeeId).getBody();

        assertNotNull(reportingStructure);
        assertEquals(4, reportingStructure.getNumberOfReports());
    }
}
