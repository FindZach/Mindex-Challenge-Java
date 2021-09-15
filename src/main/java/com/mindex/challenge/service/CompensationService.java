package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * @author Zach <zach@findzach.com>
 * @since 9/15/2021
 */
public interface CompensationService {

    Compensation create(Compensation compensation);
    Compensation read(String employeeId);
}
