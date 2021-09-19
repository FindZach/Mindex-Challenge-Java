package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
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

        //George Harrison
        String harrisonEmployeeId = "c0c2293d-16bd-4603-8e08-638a9d18b22c";
        ReportingStructure harrisonStructure = restTemplate.getForEntity(reportStructureUrl, ReportingStructure.class, harrisonEmployeeId).getBody();

        assertNotNull(harrisonStructure);
        assertEquals(0, harrisonStructure.getNumberOfReports());

        //John Lennon employeeId
        String lennonEmployeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        ReportingStructure lennonStructure = restTemplate.getForEntity(reportStructureUrl, ReportingStructure.class, lennonEmployeeId).getBody();

        assertNotNull(lennonStructure);
        assertEquals(4, lennonStructure.getNumberOfReports());


        //Zach Smith employeeId
        //DirectReport is 'John Lennon'
        String zachEmployeeId = "16a596ae-edd3-4847-99fe-c4518e82c76f";

        ReportingStructure zachStructure = restTemplate.getForEntity(reportStructureUrl, ReportingStructure.class, zachEmployeeId).getBody();

        assertNotNull(zachStructure);
        assertEquals(5, zachStructure.getNumberOfReports());
        assertEquals(lennonEmployeeId, zachStructure.getEmployee().getDirectReports().get(0).getEmployeeId());
        assertEquals(1, zachStructure.getEmployee().getDirectReports().size());


        //Cody Smith employeeId
        //DirectReport is 'Zach Smith'
        String codyEmployeeId = "16a596ae-edd3-4847-99fe-3123123";

        ReportingStructure codyStructure = restTemplate.getForEntity(reportStructureUrl, ReportingStructure.class, codyEmployeeId).getBody();

        assertNotNull(codyStructure);
        assertEquals(6, codyStructure.getNumberOfReports());
        assertEquals(zachEmployeeId, codyStructure.getEmployee().getDirectReports().get(0).getEmployeeId());
        assertEquals(1, codyStructure.getEmployee().getDirectReports().size());

        //Dave Smith employeeId
        //DirectReport is 'Cody Smith'
        String daveEmployeeId = "16a596ae-edd3-4847-99fe-55512341";

        ReportingStructure daveStructure = restTemplate.getForEntity(reportStructureUrl, ReportingStructure.class, daveEmployeeId).getBody();

        assertNotNull(daveStructure);
        assertEquals(7, daveStructure.getNumberOfReports());
        assertEquals(codyEmployeeId, daveStructure.getEmployee().getDirectReports().get(0).getEmployeeId());
        assertEquals(1, daveStructure.getEmployee().getDirectReports().size());
    }
}
