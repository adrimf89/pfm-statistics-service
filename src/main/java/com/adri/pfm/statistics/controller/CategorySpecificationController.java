package com.adri.pfm.statistics.controller;

import com.adri.pfm.commons.controller.PfmController;
import com.adri.pfm.statistics.dto.category.CategorySpecificationDTO;
import com.adri.pfm.statistics.dto.category.CreateCategorySpecificationDTO;
import com.adri.pfm.statistics.mapper.category.CategorySpecificationMapper;
import com.adri.pfm.statistics.model.category.CategorySpecification;
import com.adri.pfm.statistics.service.category.CategorySpecificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/category-specifications")
public class CategorySpecificationController implements PfmController {

    private final CategorySpecificationMapper categorySpecificationMapper;
    private final CategorySpecificationService categorySpecificationService;

    @PostMapping
    public ResponseEntity<CategorySpecificationDTO> createCategorySpecification(@RequestBody CreateCategorySpecificationDTO createCategorySpecificationDTO) {
        CategorySpecification categorySpecification = categorySpecificationMapper.toCategorySpecification(createCategorySpecificationDTO);
        categorySpecification = categorySpecificationService.createCategorySpecification(categorySpecification);
        return ResponseEntity.ok(categorySpecificationMapper.toCategorySpecificationDTO(categorySpecification));
    }
}
