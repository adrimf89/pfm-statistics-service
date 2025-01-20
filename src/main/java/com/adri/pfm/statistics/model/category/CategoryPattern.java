package com.adri.pfm.statistics.model.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryPattern {

    @Field(name = "equals_to")
    private String equalsTo;
    @Field(name = "regex")
    private String regex;

}
