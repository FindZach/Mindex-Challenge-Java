package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

import java.util.List;

/**
 * @author Zach <zach@findzach.com>
 * @since 9/15/2021
 */
public interface CompensationService extends CrudService<Compensation, String>{
    List<Compensation> getCompensationHistory(String employeeId);
}
