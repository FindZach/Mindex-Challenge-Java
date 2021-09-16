package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * @author Zach S <zach@findzach.com>
 * @since 9/15/2021
 */
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    private CompensationRepository compensationRepository;
    private EmployeeService employeeService;

    @Autowired
    public CompensationServiceImpl(CompensationRepository compensationRepository, EmployeeService employeeService) {
        this.compensationRepository = compensationRepository;
        this.employeeService = employeeService;
    }

    @Override
    public Compensation create(Compensation compensation) {
        if (employeeService.read(compensation.getEmployeeId()) != null) {
            LOG.info("Setting compensation for employeeId: " + compensation.getEmployeeId());
            return compensationRepository.insert(compensation);
        }
        return null;
    }

    @Override
    public List<Compensation> read(String employeeId) {
        List<Compensation> compensations = compensationRepository.findByEmployeeId(employeeId);
        //if list is > 1 we will sort by date before returning
        if (compensations.size() > 1) {
            compensations.sort(Comparator.comparing(Compensation::getEffectiveDate).reversed());
            return compensations;
        }
        return compensations;
    }
}
