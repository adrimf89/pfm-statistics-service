package com.adri.pfm.statistics.service.category;

import com.adri.pfm.statistics.model.category.CategorySpecification;
import com.adri.pfm.statistics.repository.category.CategorySpecificationRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.micrometer.common.util.StringUtils.isNotEmpty;
import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategorySpecificationService {

    private final CategorySpecificationRepository categorySpecificationRepository;

    public CategorySpecification createCategorySpecification(CategorySpecification newCategorySpecification) {
        Preconditions.checkArgument(nonNull(newCategorySpecification), "Category specification cannot be null");
        Preconditions.checkArgument(isNotEmpty(newCategorySpecification.getName()), "Category specification name cannot be null");
        log.info("Creating new category specification with name '{}'", newCategorySpecification.getName());
        return categorySpecificationRepository.save(newCategorySpecification);
    }

    public List<CategorySpecification> findAllOrdered() {
        return categorySpecificationRepository.findAll(Sort.by(Sort.Direction.ASC, "order"));
    }
}
