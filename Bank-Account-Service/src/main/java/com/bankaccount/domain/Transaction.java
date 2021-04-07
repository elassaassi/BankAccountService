package com.bankaccount.domain;

import com.bankaccount.services.TransactionService;
import com.bankaccount.services.OperationService;
import com.bankaccount.utils.OperationType;

import java.time.LocalDateTime;

public class Transaction {

	private final OperationType operationType;
	private final Wallet wallet;
	private final LocalDateTime dateTime;

	private Transaction(final OperationType pOperationType, final Wallet pAmount, final LocalDateTime pTimestamp) {
		this.operationType = pOperationType;
		this.dateTime = pTimestamp;
		this.wallet = pAmount;
	}

	public static Transaction getInstance(final OperationType operationType, final LocalDateTime dateTime,
			final Wallet wallet) {
		return new Transaction(operationType, wallet, dateTime);
	}

	public OperationService processOperation(final TransactionService TransactionService) {
		final TransactionService nextAccountPaiement = operationType.process(TransactionService, wallet);
		return OperationService.get(operationType, dateTime, wallet, nextAccountPaiement);
	}
}
