package com.adri.pfm.statistics.service.transaction;

import com.adri.pfm.commons.exception.ResourceNotFoundException;
import com.adri.pfm.statistics.model.transaction.Transaction;
import com.adri.pfm.statistics.model.transaction.TransactionStatsStatus;
import com.adri.pfm.statistics.service.statistic.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandleTransactionCategorizedService {

    private final StatusService statusService;
    private final StatisticService statisticService;
    private final TransactionService transactionService;

    public void handleTransactionCategorized(long transactionId) {
        log.info("Handle CATEGORIZED transaction");
        try {
            Transaction transaction = transactionService.findTransactionByTransactionId(transactionId);
            calculateStatistics(transaction);
        } catch (ResourceNotFoundException e) {
            log.error("Error while trying to calculate statistics of transaction '{}'. Transaction not found in database.", transactionId, e);
        } catch (Exception e) {
            log.error("Error while to trying to calculate statistics of transaction '{}'", transactionId, e);
        }
    }

    private void calculateStatistics(Transaction transaction) {
        log.info("Starting calculating statistics for transaction with id '{}'", transaction.getTransactionId());
        statisticService.addTransactionToStatistic(transaction);
        statusService.createNewTransactionStatus(transaction, TransactionStatsStatus.COUNTED);
    }
}
