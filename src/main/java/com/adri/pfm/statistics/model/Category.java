package com.adri.pfm.statistics.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class Category {
    private String name;
    @Field(name = "category_group")
    private String categoryGroup;
}
