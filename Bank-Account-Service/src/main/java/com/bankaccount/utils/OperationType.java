package com.bankaccount.utils;

import com.bankaccount.services.TransactionService;
import com.bankaccount.domain.Wallet;

import java.util.function.BiFunction;

public enum OperationType {

    CREDIT(TransactionService::credit),
    DEBIT(TransactionService::debit);

    public BiFunction<TransactionService, Wallet, TransactionService> operation;

    OperationType(final BiFunction<TransactionService, Wallet, TransactionService> operation) {
        this.operation = operation;
    }

    public TransactionService process(final TransactionService accountBalance, final Wallet wallet) {
        return operation.apply(accountBalance, wallet);
    }
}
