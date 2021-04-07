package com.bankaccount.services;


import com.bankaccount.domain.Wallet;
import com.bankaccount.services.TransactionService;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionServiceTest {

    @Test
    public void init_paiement_test() {
        final TransactionService transactionService = TransactionService.init();

        assertThat(transactionService).isEqualTo(TransactionService.get(0L));
    }

    @Test
    public void credit_account_test() {
        final TransactionService transactionService = TransactionService.get(2000L);
        final Wallet wallet = Wallet.init(4000L);

        final TransactionService result = transactionService.credit(wallet);

        assertThat(result).isEqualTo(TransactionService.get(6000L));
    }

    @Test
    public void debit_account_test() {
        final TransactionService transactionService = TransactionService.get(5000L);
        final Wallet amount = Wallet.init(2000L);

        final TransactionService result = transactionService.debit(amount);

        assertThat(result).isEqualTo(TransactionService.get(3000L));
    }
}