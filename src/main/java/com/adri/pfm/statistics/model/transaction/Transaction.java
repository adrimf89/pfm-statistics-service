package com.adri.pfm.statistics.model.transaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Document("transaction")
public class Transaction {

    @Id
    private String id;
    @Field("transaction_id")
    private long transactionId;
    private long accountId;
    private String concept;
    private Date date;
    private BigDecimal amount;
    private Category category;

}
