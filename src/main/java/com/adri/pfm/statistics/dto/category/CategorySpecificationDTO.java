package com.adri.pfm.statistics.dto.category;

import java.util.List;

public record CategorySpecificationDTO(String id, String name, int order, String categoryGroup, List<CategoryPatternDTO> categoryPatterns) {
}
