package com.adri.pfm.statistics.mapper.transaction;

import com.adri.pfm.commons.jms.message.TransactionMessage;
import com.adri.pfm.statistics.model.transaction.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toTransaction(TransactionMessage transactionMessage);
}
