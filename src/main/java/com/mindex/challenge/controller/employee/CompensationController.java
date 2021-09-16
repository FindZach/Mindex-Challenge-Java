package com.mindex.challenge.controller.employee;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zach S <zach@findzach.com>
 * @since 9/15/2021
 */
@RestController
public class CompensationController {

    @Autowired
    private CompensationService compensationService;

    /**
     * Our Service Layer sorts our CompensationList by date, latest is index 0
     * @param employeeId
     * @return
     */
    @GetMapping("/employee/{employeeId}/compensation")
    public Compensation retrieveLatestCompensation(@PathVariable String employeeId) {
        return compensationService.read(employeeId).get(0);
    }

    /**
     * Perhaps we want to keep track of past compensation records incase we want to display this information
     * @param employeeId
     * @return List of Compensation records, if any exist.
     */
    @GetMapping("/employee/{employeeId}/compensation/history")
    public List<Compensation> retrieveCompensationHistory(@PathVariable String employeeId) {
        return compensationService.read(employeeId);
    }

    /**
     * Submits a Compensation record, automatically timestamps.
     * @param compensation
     * @return
     */
    @PostMapping("/employee/compensation")
    public Compensation createCompensation(@RequestBody Compensation compensation) {
        return compensationService.create(compensation);
    }
}
