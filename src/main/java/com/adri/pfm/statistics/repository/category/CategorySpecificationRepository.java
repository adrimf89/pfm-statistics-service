package com.adri.pfm.statistics.repository.category;

import com.adri.pfm.statistics.model.category.CategorySpecification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorySpecificationRepository extends MongoRepository<CategorySpecification, String> {
}
