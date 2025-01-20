package com.adri.pfm.statistics.dto.category;

import java.util.List;

public record CreateCategorySpecificationDTO(String name, int order, String categoryGroup, List<CategoryPatternDTO> categoryPatterns) {
}
