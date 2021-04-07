package com.bankaccount.services;

import static com.bankaccount.utils.OperationType.CREDIT;
import static com.bankaccount.utils.OperationType.DEBIT;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mock;

import com.bankaccount.services.TransactionService;

import com.bankaccount.domain.Wallet;

public class OperationTypeTest {
	/**
	 * Credit Operation Processing ...
	 */
	@Test
	public void credit_operation_processing_test_OK() {
		final TransactionService transactionService = mock(TransactionService.class);
		final Wallet amount = mock(Wallet.class);

		CREDIT.process(transactionService, amount);

		verify(transactionService).credit(amount);
		verify(transactionService, times(0)).debit(amount);
	}

	/**
	 * Debit Operation Processing ...
	 */
	@Test
	public void debit_operation_processing_test_OK() {
		final TransactionService transactionService=mock(TransactionService.class);
		final Wallet wallet = mock(Wallet.class);

		DEBIT.process(transactionService, wallet);

		verify(transactionService).debit(wallet);
		verify(transactionService, times(0)).credit(wallet);
	}
}