package com.adri.pfm.statistics.model.statistic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@Document("statistic")
public class Statistic {

    @Id
    private String id;
    private Integer year;
    private Integer month;
    private long accountId;
    private BigDecimal amount;
    private String category;
    @Field(name = "category_group")
    private String categoryGroup;
}
