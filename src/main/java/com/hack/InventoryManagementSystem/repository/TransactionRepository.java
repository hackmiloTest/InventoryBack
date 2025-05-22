package com.hack.InventoryManagementSystem.repository;

import com.hack.InventoryManagementSystem.entity.Transaction;
import com.hack.InventoryManagementSystem.enums.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t " +
            "WHERE YEAR(t.createdAt) = :year AND MONTH(t.createdAt) = :month")
    List<Transaction> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);


    @Query("SELECT t FROM Transaction t LEFT JOIN FETCH t.product p " +
            "WHERE (:searchText = '' OR LOWER(t.description) LIKE LOWER(CONCAT('%', :searchText, '%'))) " +
            "AND (:transactionType IS NULL OR " +
            "   (:#{#transactionType?.name()} = 'SALE_NO_RETURN' AND t.transactionType = com.hack.InventoryManagementSystem.enums.TransactionType.SALE AND NOT EXISTS (" +
            "       SELECT 1 FROM Transaction r WHERE r.originalSaleId = t.id AND r.transactionType = com.hack.InventoryManagementSystem.enums.TransactionType.RETURN" +
            "   )) OR " +
            "   (:#{#transactionType?.name()} != 'SALE_NO_RETURN' AND t.transactionType = :transactionType)" +
            ") " +
            "ORDER BY t.createdAt DESC")
    Page<Transaction> searchTransactions(
            @Param("searchText") String searchText,
            @Param("transactionType") String transactionType,
            Pageable pageable);


    @Query("SELECT t FROM Transaction t WHERE " +
            "(LOWER(t.description) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR LOWER(t.product.name) LIKE LOWER(CONCAT('%', :searchText, '%'))) " +
            "AND (:transactionType IS NULL OR t.transactionType = :transactionType)")
    Page<Transaction> searchTransactionsWithType(@Param("searchText") String searchText,
                                                 @Param("transactionType") TransactionType transactionType,
                                                 Pageable pageable);

    @Query("SELECT t FROM Transaction t " +
            "LEFT JOIN FETCH t.product " +
            "LEFT JOIN FETCH t.user " +
            "LEFT JOIN FETCH t.supplier " +
            "WHERE t.id = :id")
    Optional<Transaction> findByIdWithDetails(@Param("id") Long id);


}
