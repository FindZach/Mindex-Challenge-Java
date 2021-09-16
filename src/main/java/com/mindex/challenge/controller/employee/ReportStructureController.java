package com.mindex.challenge.controller.employee;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportStructureService;
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

    @Autowired
    private ReportStructureService reportStructureService;


    @GetMapping("employee/{employeeId}/reportstructure")
    public ReportingStructure retrieveReportStructure(@PathVariable String employeeId) {
        return reportStructureService.retrieve(employeeId);
    }
}
