package com.adri.pfm.statistics.model.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Document("category_specification")
public class CategorySpecification {

    @Id
    private String id;
    private String name;
    private int order;
    @Field(name = "category_group")
    private String categoryGroup;
    @Field(name = "category_patterns")
    private List<CategoryPattern> categoryPatterns;
}
