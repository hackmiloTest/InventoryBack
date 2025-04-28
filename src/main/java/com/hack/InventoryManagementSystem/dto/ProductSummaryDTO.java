package com.hack.InventoryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSummaryDTO {
    private Map<String, Long> totalProductsByCategory;
    private long totalAvailableStock;
}

