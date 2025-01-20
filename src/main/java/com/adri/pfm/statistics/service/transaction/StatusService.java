package com.adri.pfm.statistics.service.transaction;

import com.adri.pfm.commons.exception.RepositoryException;
import com.adri.pfm.statistics.model.transaction.Status;
import com.adri.pfm.statistics.model.transaction.Transaction;
import com.adri.pfm.statistics.model.transaction.TransactionStatsStatus;
import com.adri.pfm.statistics.repository.transaction.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;

    public void createNewTransactionStatus(Transaction transaction, TransactionStatsStatus status) {
        Status newStatus = new Status();
        newStatus.setStatus(status);
        newStatus.setStatusChangeDate(new Date());
        newStatus.setTransactionId(transaction.getTransactionId());
        save(newStatus);
    }

    private void save(Status status) {
        try {
            status = statusRepository.save(status);
            log.debug("Transaction status saved with id '{}'", status.getId());
        } catch (Exception e) {
            log.error("Exception while saving Transaction status", e);
            throw new RepositoryException("Exception while saving Transaction status");
        }
    }

}
