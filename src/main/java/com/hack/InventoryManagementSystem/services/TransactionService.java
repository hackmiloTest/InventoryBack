package com.hack.InventoryManagementSystem.services;

import com.hack.InventoryManagementSystem.dto.Response;
import com.hack.InventoryManagementSystem.dto.TransactionsRequest;
import com.hack.InventoryManagementSystem.enums.TransactionStatus;
import com.hack.InventoryManagementSystem.enums.TransactionType;

public interface TransactionService {
    Response restockInventory(TransactionsRequest transactionsRequest);

    Response sell(TransactionsRequest transactionsRequest);

    Response returnToSupplier(TransactionsRequest transactionsRequest);

    Response getAllTransactions(int page, int size, String searchText, String transactionType);

    Response getTransactionsById(Long id);

    Response getAllTransactionsByMonthAndYear(int month, int year);

    Response updateTransactions(Long transactionId, TransactionStatus transactionStatus);
    Response returnSaleTransaction(TransactionsRequest transactionsRequest, Long originalSaleId);
}
