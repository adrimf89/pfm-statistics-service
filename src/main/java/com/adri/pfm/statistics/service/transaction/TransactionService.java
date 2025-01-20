package com.adri.pfm.statistics.service.transaction;

import com.adri.pfm.commons.exception.RepositoryException;
import com.adri.pfm.commons.exception.ResourceNotFoundException;
import com.adri.pfm.commons.jms.message.TransactionMessage;
import com.adri.pfm.statistics.mapper.transaction.TransactionMapper;
import com.adri.pfm.statistics.model.category.CategorySpecification;
import com.adri.pfm.statistics.model.transaction.Category;
import com.adri.pfm.statistics.model.transaction.Transaction;
import com.adri.pfm.statistics.model.transaction.TransactionStatsStatus;
import com.adri.pfm.statistics.repository.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final StatusService statusService;
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;

    public Transaction findTransactionByTransactionId(long transactionId) {
        return transactionRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found for id "+transactionId));
    }

    public void handleTransactionStatusChange(final TransactionMessage transactionMessage) {
        if ("PROCESSED".equals(transactionMessage.status())) {
            log.info("Transaction with id '{}' PROCESSED. Creating new record", transactionMessage.transactionId());
            Transaction transaction = transactionMapper.toTransaction(transactionMessage);
            save(transaction);
            statusService.createNewTransactionStatus(transaction, TransactionStatsStatus.RECEIVED);
        } else {
            log.info("No action required for transaction in status '{}'", transactionMessage.status());
        }
    }

    public void updateTransactionCategory(Transaction transaction, CategorySpecification matchedCategory) {
        Category category = new Category();
        category.setName(matchedCategory.getName());
        category.setCategoryGroup(matchedCategory.getCategoryGroup());
        transaction.setCategory(category);
        save(transaction);
    }

    private void save(Transaction transaction) {
        try {
            transaction = transactionRepository.save(transaction);
            log.debug("Transaction saved with id '{}'", transaction.getTransactionId());
        } catch (Exception e) {
            log.error("Exception while saving Transaction ", e);
            throw new RepositoryException("Exception while saving Transaction");
        }
    }
}
