package com.example.demo.Sercive;

import com.example.demo.Entity.TransactionHistory;
import com.example.demo.Repository.TransactionHistoryRepository;
import com.example.demo.Sercive.impl.TransactionHistoryServiceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionHistoryService implements TransactionHistoryServiceimpl {
    @Override
    public List<TransactionHistory> GetTransactionHistoryUser_id(Long id) {
        return transactionHistoryRepository.findByUserId(id);
    }

    private final TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public void SaveTransactionHistory(TransactionHistory transactionHistory) {
        transactionHistoryRepository.save(transactionHistory);
    }
}
