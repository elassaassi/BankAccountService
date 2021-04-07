package com.bankaccount.services;


import static com.bankaccount.utils.OperationType.CREDIT;
import static com.bankaccount.utils.OperationType.DEBIT;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

import com.bankaccount.domain.Account;
import com.bankaccount.domain.Wallet;

public class AccountTestAcceptance {

	@Test
	public void credit_account_service_Test_OK() {
		final LocalDateTime actualDateTime = LocalDateTime.now();
		final Account account = Account.get(Wallet.init(2000L), () -> actualDateTime);

		final OperationService credit = account.credit(Wallet.init(5000L));

		assertThat(credit).isEqualTo(
				OperationService.get(CREDIT, actualDateTime, Wallet.init(5000L), TransactionService.get(7000L)));
	}

	@Test
	public void debit_account_service_Test_OK() {
		final LocalDateTime actualDateTime = LocalDateTime.now();
		final Account account = Account.get(Wallet.init(7000L), () -> actualDateTime);

		final OperationService operation = account.debit(Wallet.init(1000L));

		assertThat(operation).isEqualTo(
				OperationService.get(DEBIT, actualDateTime, Wallet.init(1000L), TransactionService.get(6000L)));
	}
	

	@Test
	public void debit_account_neg_wallet_Test_OK() {

		final LocalDateTime actualDateTime = LocalDateTime.now();
		final Account account = Account.get(Wallet.init(5000L), () -> actualDateTime);
		final OperationService operation = account.debit(Wallet.init(10000L));

		assertThat(operation)
				.isEqualTo(OperationService.get(DEBIT, actualDateTime, Wallet.init(10000L), TransactionService.get(-5000L)));
	}

	
}
