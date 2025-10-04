package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Transaction;

public interface TransactionServiceimpl {
    Transaction SaveTransaction(Transaction transaction);
    Transaction GetTransaction(Long id);

}
