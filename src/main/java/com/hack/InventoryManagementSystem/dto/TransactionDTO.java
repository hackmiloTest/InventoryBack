package com.hack.InventoryManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hack.InventoryManagementSystem.enums.TransactionType;
import com.hack.InventoryManagementSystem.enums.TransactionStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {

    private Long id;
    private Integer totalProducts;
    private BigDecimal totalPrice;
    private TransactionType transactionType;
    private TransactionStatus status;
    private String description;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private Long originalSaleId;

    private ProductDTO product; // Cambiado de ManyToMany a solo referencia directa
    private UserDTO user;
    private SupplierDTO supplier;
}
