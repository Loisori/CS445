package com.example.demo.Sercive;

import com.example.demo.Entity.Transaction;
import com.example.demo.Repository.TransactionRepository;
import com.example.demo.Sercive.impl.TransactionServiceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactitonService implements TransactionServiceimpl {
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction GetTransaction(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }


    @Override
    public Transaction SaveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
