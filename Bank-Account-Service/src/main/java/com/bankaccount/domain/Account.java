package com.bankaccount.domain;

import com.bankaccount.services.*;
import com.bankaccount.utils.OperationType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

import static com.bankaccount.utils.OperationType.CREDIT;
import static com.bankaccount.utils.OperationType.DEBIT;

public class Account {

	private AccountService accountService;
	private final Supplier<LocalDateTime> actualDTimeSuppl;

	private Account(final Supplier<LocalDateTime> actualDTimeSuppl, final AccountService accountService) {
		this.actualDTimeSuppl = actualDTimeSuppl;
		this.accountService = accountService;
	}

	public static Account get(final Wallet wallet, final Supplier<LocalDateTime> actualDTimeSuppl) {
		final Account account = new Account(actualDTimeSuppl, AccountService.init());
		account.credit(wallet);

		return account;
	}

	public OperationService credit(final Wallet amount) {
		return updateOperation(CREDIT, amount);
	}

	public OperationService debit(final Wallet amount) {
		return updateOperation(DEBIT, amount);
	}

	private OperationService updateOperation(final OperationType operationType, final Wallet amount) {
		final Transaction operation = Transaction.getInstance(operationType, actualDTimeSuppl.get(), amount);
		accountService = accountService.updateOperation(operation);

		return accountService.lastOperation();
	}

	public List<OperationService> getOperations() {
		return accountService.getOperations();
	}
}
