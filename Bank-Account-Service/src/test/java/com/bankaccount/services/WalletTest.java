package com.bankaccount.services;


import com.bankaccount.domain.Wallet;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WalletTest {

    @Test(expected = IllegalArgumentException.class)
    public void wallet_initialization_test_KO() {
        Wallet.init(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalid_negatif_init_test_KO() {
        Wallet.init(-1L);
    }

    @Test
    public void credit_money_to_wallet_test_OK() {
        final Wallet wallet = Wallet.init(3000L);

        final long result = wallet.creditMoneyInEuro(4000L);

        assertThat(result).isEqualTo(7000L);
    }

    @Test
    public void debit_money_to_wallet_test_OK() {
        final Wallet wallet = Wallet.init(2L);

        final long result = wallet.debitMoneyInEuro(4L);

        assertThat(result).isEqualTo(2L);
    }
}