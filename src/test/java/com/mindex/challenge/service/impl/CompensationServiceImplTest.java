package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Zach S <zach@findzach.com>
 * @since 9/15/2021
 *
 * Will test and verify that we can properly create/read our Compensation data
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationPostUrl;
    private String compensationDataUrl;

    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationPostUrl = "http://localhost:" + port + "/employee/compensation";
        compensationDataUrl = "http://localhost:" + port + "/employee/{employeeId}/compensation";
    }

    @Test
    public void testCreateRead() {
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        Compensation compensation = new Compensation();
        compensation.setEmployeeId(employeeId);
        compensation.setSalary(100_000);

        Compensation savedCompensation = restTemplate.postForEntity(compensationPostUrl, compensation, Compensation.class).getBody();

        assertNotNull(savedCompensation); //Verifying we're getting our obj returned when we post

        assertEquals(100_000, savedCompensation.getSalary(), 0);

        assertEquals(employeeId, savedCompensation.getEmployeeId());

        Compensation loadedComp = restTemplate.getForEntity(compensationDataUrl, Compensation.class, employeeId).getBody();

        assertNotNull(loadedComp);
        assertEquals(100_000, loadedComp.getSalary(), 0);

    }

}
