package com.adri.pfm.statistics.mapper.category;

import com.adri.pfm.statistics.dto.category.CategorySpecificationDTO;
import com.adri.pfm.statistics.dto.category.CreateCategorySpecificationDTO;
import com.adri.pfm.statistics.model.category.CategorySpecification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategorySpecificationMapper {

    CategorySpecification toCategorySpecification(CreateCategorySpecificationDTO createCategorySpecificationDTO);
    CategorySpecificationDTO toCategorySpecificationDTO(CategorySpecification categorySpecification);
}
