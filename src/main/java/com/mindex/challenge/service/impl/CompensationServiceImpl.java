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
import java.util.Set;

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
    public Set<Compensation> findAll() {
        return Set.copyOf(compensationRepository.findAll());
    }

    @Override
    public Compensation findById(String id) {
        return compensationRepository.findById(id).isPresent() ? compensationRepository.findById(id).get() : null;
    }

    @Override
    public Compensation create(Compensation compensation) {
        if (employeeService.findById(compensation.getEmployeeId()) != null) {
            LOG.info("Setting compensation for employeeId: " + compensation.getEmployeeId());
            return compensationRepository.save(compensation);
        }
        //Not a valid employee
        return null;
    }

    @Override
    public void delete(Compensation object) {
        compensationRepository.delete(object);
    }

    @Override
    public Compensation updateWithId(String id, Compensation object) {
        if (compensationRepository.findById(id).isPresent()) {
            return compensationRepository.save(object);
        } else return null;
    }

    @Override
    public Compensation update(Compensation object) {
        return compensationRepository.save(object);
    }

    /**
     * Deletes Object , finds with ID
     *
     * @param id - The ID of the Compensation object
     * @return - True if deleted, false if no record exists
     */
    @Override
    public boolean deleteById(String id) {
        compensationRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Compensation> getCompensationHistory(String employeeId) {
        List<Compensation> compensations = compensationRepository.findByEmployeeId(employeeId);
        //if list is > 1 we will sort by date before returning
        if (compensations.size() > 1) {
            compensations.sort(Comparator.comparing(Compensation::getEffectiveDate).reversed());
            return compensations;
        }
        return compensations;
    }
}
