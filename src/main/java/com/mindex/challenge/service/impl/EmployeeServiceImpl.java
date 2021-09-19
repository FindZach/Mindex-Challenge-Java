package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Set<Employee> findAll() {
        LOG.debug("Retrieving set of employees");

        return Set.copyOf(employeeRepository.findAll());
    }

    @Override
    public Employee findById(String id) {
        LOG.debug("Finding employee with ID [{}]", id);

        return employeeRepository.findByEmployeeId(id);
    }

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public void delete(Employee employee) {
        LOG.debug("Deleting employee with ID [{}]", employee);

        employeeRepository.delete(employee);
    }

    @Override
    public Employee updateWithId(String id, Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        if (employeeRepository.findByEmployeeId(id) != null) {
            return employeeRepository.save(employee);
        }
        return null;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    /**
     * Deletes Object , finds with ID
     *
     * @param id - The ID of the object
     * @return - True if deleted, false if no record exists
     */
    @Override
    public boolean deleteById(String id) {
        if (employeeRepository.findByEmployeeId(id) != null) {
            LOG.debug("Deleting Employee with ID [{}]", id);

            employeeRepository.deleteById(id);
            return true;
        } else {
            LOG.debug("Unable to delete Employee(Invalid ID) with ID [{}]", id);

            return false;
        }
    }
}
