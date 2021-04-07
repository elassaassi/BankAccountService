package com.bankaccount.services;

import com.bankaccount.domain.Wallet;
import com.bankaccount.domain.Transaction;
import com.bankaccount.utils.OperationType;

import java.time.LocalDateTime;
import java.util.Objects;

public class OperationService {

	private final OperationType operationType;
	private final LocalDateTime actualDateTime;
	private final Wallet wallet;
	private final TransactionService transactionService;

	private OperationService(final OperationType operationType, final LocalDateTime actualDateTime, final Wallet wallet,
			final TransactionService transactionService) {
		this.operationType = operationType;
		this.actualDateTime = actualDateTime;
		this.wallet = wallet;
		this.transactionService = transactionService;
	}

	public static OperationService get(final OperationType operationType, final LocalDateTime actualDateTime,
			final Wallet wallet, final TransactionService transactionService) {
		return new OperationService(operationType, actualDateTime, wallet, transactionService);
	}

	OperationService calculateNext(final Transaction transaction) {
		return transaction.processOperation(transactionService);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final OperationService that = (OperationService) o;
		return operationType == that.operationType && Objects.equals(actualDateTime, that.actualDateTime)
				&& Objects.equals(wallet, that.wallet) && Objects.equals(transactionService, that.transactionService);
	}

	@Override
	public int hashCode() {
		return Objects.hash(operationType, actualDateTime, wallet, transactionService);
	}

	@Override
	public String toString() {
		return "Operation Service :" + "operationType=" + operationType + ", actualDateTime=" + actualDateTime
				+ ", wallet=" + wallet + ", Solde =" + transactionService + '}';
	}
}
