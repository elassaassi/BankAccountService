package com.bankaccount.services;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.bankaccount.domain.Transaction;

public class AccountService {

	private final List<OperationService> operationServices;

	private AccountService(final List<OperationService> operationServices) {
		this.operationServices = operationServices;
	}

	public static AccountService init() {
		return new AccountService(emptyList());
	}

	static AccountService getLists(final List<OperationService> operationServices) {
		return new AccountService(operationServices);
	}

	AccountService addOperation(final OperationService operation) {
		final List<OperationService> statements = new ArrayList<>(this.operationServices);
		statements.add(operation);

		return new AccountService(statements);
	}

	public AccountService updateOperation(final Transaction pTransaction) {
		final OperationService lastStatement = this.lastOperation();
		final OperationService nextStatement = Objects.nonNull(lastStatement)
				? lastStatement.calculateNext(pTransaction)
				: pTransaction.processOperation(TransactionService.init());

		return addOperation(nextStatement);
	}

	public List<OperationService> getOperations() {
		return new ArrayList<>(operationServices);
	}

	public OperationService lastOperation() {
		if (operationServices.isEmpty())
			return null;

		return operationServices.get(operationServices.size() - 1);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final AccountService accountStatement = (AccountService) o;
		return Objects.equals(operationServices, accountStatement.operationServices);
	}

	@Override
	public int hashCode() {
		return Objects.hash(operationServices);
	}

	@Override
	public String toString() {
		return "Account Operations :  " + operationServices;
	}
}
