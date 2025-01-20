package com.adri.pfm.statistics.event.listener;

import com.adri.pfm.statistics.model.transaction.Status;
import com.adri.pfm.statistics.service.transaction.HandleTransactionCategorizedService;
import com.adri.pfm.statistics.service.transaction.HandleTransactionReceivedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AfterTransactionSaveListener extends AbstractMongoEventListener<Status> {

    private final HandleTransactionReceivedService handleTransactionReceivedService;
    private final HandleTransactionCategorizedService handleTransactionCategorizedService;

    @Override
    public void onAfterSave(AfterSaveEvent<Status> event) {
        Status status = event.getSource();
        log.info("New transaction status saved for transaction id '{}' and status '{}'",
                status.getTransactionId(),
                status.getStatus());

        switch (status.getStatus()) {
            case RECEIVED -> handleTransactionReceivedService.handleTransactionReceived(status.getTransactionId());
            case CATEGORIZED -> handleTransactionCategorizedService.handleTransactionCategorized(status.getTransactionId());
            default -> log.info("No business logic for status '{}'", status.getStatus());
        }
    }
}
