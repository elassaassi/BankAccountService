package com.bankaccount.services;


import static com.bankaccount.utils.OperationType.CREDIT;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.bankaccount.domain.Transaction;
import com.bankaccount.domain.Wallet;
import com.bankaccount.services.OperationService;
import com.bankaccount.services.TransactionService;

public class TransactionTest {
	@Test
	public void operation_service_processing_test_OK() {
		final Wallet wallet = mock(Wallet.class);
		final LocalDateTime actualDateTime = LocalDateTime.now();

		final TransactionService transactionService = mock(TransactionService.class);
		final TransactionService expectedTransactionService = TransactionService.get(1L);

		final Transaction transaction = Transaction.getInstance(CREDIT, actualDateTime, wallet);

		when(transactionService.credit(wallet)).thenReturn(expectedTransactionService);
		final OperationService operation = transaction.processOperation(transactionService);

		Assertions.assertThat(operation)
				.isEqualTo(OperationService.get(CREDIT, actualDateTime, wallet, expectedTransactionService));
	}

	@Test
	public void transaction_next_operation_test_OK() {
		final TransactionService accountBalance = mock(TransactionService.class);
		final Transaction transaction = mock(Transaction.class);
		final OperationService operation = OperationService.get(CREDIT, LocalDateTime.now(), mock(Wallet.class),
				accountBalance);

		operation.calculateNext(transaction);

		verify(transaction).processOperation(accountBalance);
	}

}