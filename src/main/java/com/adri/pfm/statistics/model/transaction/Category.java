package com.adri.pfm.statistics.model.transaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@RequiredArgsConstructor
public class Category {
    private String name;
    @Field(name = "category_group")
    private String categoryGroup;
}
