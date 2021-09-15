package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Zach S <zach@findzach.com>
 * @since 9/15/2021
 */
public interface CompensationRepository extends MongoRepository<Compensation, String> {
}
