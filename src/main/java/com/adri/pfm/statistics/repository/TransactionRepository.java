package com.adri.pfm.statistics.repository;

import com.adri.pfm.statistics.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
