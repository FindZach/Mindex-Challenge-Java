package com.mindex.challenge.controller.employee;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zach S <zach@findzach.com>
 * @since 9/15/2021
 * Will listen for ReportStructure requests
 */
@RestController
public class ReportStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportStructureController.class);

    @Autowired
    private ReportStructureService reportStructureService;


    @GetMapping("employee/{employeeId}/reportstructure")
    public ReportingStructure retrieveReportStructure(@PathVariable String employeeId) {
        LOG.debug("Received ReportStructure request for [{}]", employeeId);

        return reportStructureService.retrieve(employeeId);
    }
}
