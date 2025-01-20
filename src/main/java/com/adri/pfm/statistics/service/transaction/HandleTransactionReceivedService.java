package com.adri.pfm.statistics.service.transaction;

import com.adri.pfm.commons.exception.ResourceNotFoundException;
import com.adri.pfm.statistics.model.category.CategoryPattern;
import com.adri.pfm.statistics.model.category.CategorySpecification;
import com.adri.pfm.statistics.model.transaction.Transaction;
import com.adri.pfm.statistics.model.transaction.TransactionStatsStatus;
import com.adri.pfm.statistics.service.category.CategorySpecificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.micrometer.common.util.StringUtils.isNotEmpty;
import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandleTransactionReceivedService {

    private final StatusService statusService;
    private final TransactionService transactionService;
    private final CategorySpecificationService categorySpecificationService;

    public void handleTransactionReceived(long transactionId) {
        log.info("Handle RECEIVED transaction with id '{}'", transactionId);
        try {
            Transaction transaction = transactionService.findTransactionByTransactionId(transactionId);
            categorizeTransaction(transaction);
        } catch (ResourceNotFoundException e) {
           log.error("Error while trying to categorize transaction '{}'. Transaction not found in database.", transactionId, e);
        } catch (Exception e) {
            log.error("Error while trying to categorize transaction '{}'", transactionId, e);
        }
    }

    private void categorizeTransaction(Transaction transaction) {
        log.info("Starting categorization of transaction with id '{}'", transaction.getTransactionId());
        CategorySpecification matchedCategory = lookForMatchingCategory(transaction.getConcept());

        if (nonNull(matchedCategory)) {
            transactionService.updateTransactionCategory(transaction, matchedCategory);
            statusService.createNewTransactionStatus(transaction, TransactionStatsStatus.CATEGORIZED);
        } else {
            log.warn("Any category matched with transaction id '{}'", transaction.getTransactionId());
        }
    }

    private CategorySpecification lookForMatchingCategory(String transactionConcept) {
        List<CategorySpecification> categorySpecifications = categorySpecificationService.findAllOrdered();
        for (CategorySpecification categorySpecification : categorySpecifications) {
            for (CategoryPattern categoryPattern : categorySpecification.getCategoryPatterns()) {
                if (isNotEmpty(categoryPattern.getEqualsTo())
                        && transactionConcept.equals(categoryPattern.getEqualsTo())) {
                    log.info("Transaction concept '{}' matched with equals to '{}' of category specification '{}'",
                            transactionConcept, categoryPattern.getEqualsTo(), categorySpecification.getName());
                    return categorySpecification;
                } else if (transactionConcept.matches(categoryPattern.getRegex())) {
                    log.info("Transaction concept '{}' matched with regex '{}' of category specification '{}'",
                            transactionConcept, categoryPattern.getRegex(), categorySpecification.getName());
                    return categorySpecification;
                }
            }
        }
        return null;
    }
}
