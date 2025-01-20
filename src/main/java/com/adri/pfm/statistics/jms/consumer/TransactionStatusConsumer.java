package com.adri.pfm.statistics.jms.consumer;

import com.adri.pfm.commons.jms.BaseConsumer;
import com.adri.pfm.commons.jms.message.TransactionMessage;
import com.adri.pfm.statistics.config.PfmTransactionConfig;
import com.adri.pfm.statistics.service.transaction.TransactionService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionStatusConsumer extends BaseConsumer {

    private final MessageConverter messageConverter;
    private final TransactionService transactionService;
    private final PfmTransactionConfig pfmTransactionConfig;

    @Override
    protected String getConsumerQueue() {
        return pfmTransactionConfig.getTransactionStatusQueue();
    }

    @Override
    protected void processMessage(Message message) throws JMSException, MessageConversionException {
        TransactionMessage transactionMessage = (TransactionMessage) messageConverter.fromMessage(message);
        log.info("New message received for transaction id '{}' and status '{}'",
                transactionMessage.transactionId(),
                transactionMessage.status());
        transactionService.handleTransactionStatusChange(transactionMessage);
    }
}
