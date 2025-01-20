package com.adri.pfm.statistics.repository.transaction;

import com.adri.pfm.statistics.model.transaction.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    Optional<Transaction> findByTransactionId(long transactionId);
}
