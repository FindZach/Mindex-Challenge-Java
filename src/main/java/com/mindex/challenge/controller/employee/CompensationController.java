package com.mindex.challenge.controller.employee;

import com.mindex.challenge.controller.EmployeeController;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zach S <zach@findzach.com>
 * @since 9/15/2021
 */
@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    /**
     * Our Service Layer sorts our CompensationList by date, latest is index 0
     * @param employeeId - The ID of the Employee
     * @return - Returns the latest Compensation details
     */
    @GetMapping("/employee/{employeeId}/compensation")
    public Compensation retrieveLatestCompensation(@PathVariable String employeeId) {
        LOG.debug("Received active compensation view request for [{}]", employeeId);

        List<Compensation> compensations = compensationService.getCompensationHistory(employeeId);
        if (!compensations.isEmpty()) {
            return compensations.get(0);
        } else {
            return null;
        }
    }

    /**
     * Perhaps we want to keep track of past compensation records incase we want to display this information
     * @param employeeId
     * @return List of Compensation records, if any exist.
     */
    @GetMapping("/employee/{employeeId}/compensation/history")
    public List<Compensation> retrieveCompensationHistory(@PathVariable String employeeId) {
        LOG.debug("Received compensation history request for [{}]", employeeId);

        return compensationService.getCompensationHistory(employeeId);
    }

    /**
     * Submits a Compensation record, automatically timestamps.
     * @param compensation
     * @return
     */
    @PostMapping("/employee/compensation")
    public Compensation createCompensation(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation.getEmployeeId());

        return compensationService.create(compensation);
    }
}
