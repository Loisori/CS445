package com.example.demo.Sercive.impl;

import com.example.demo.Entity.TransactionHistory;

import java.util.List;

public interface TransactionHistoryServiceimpl {
     void SaveTransactionHistory(TransactionHistory transactionHistory);
     List<TransactionHistory> GetTransactionHistoryUser_id(Long id);
}
