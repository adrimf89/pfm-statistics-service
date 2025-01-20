package com.adri.pfm.statistics.repository.transaction;

import com.adri.pfm.statistics.model.transaction.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends MongoRepository<Status, String> {
}
