package com.bankaccount.services;


import static com.bankaccount.utils.OperationType.CREDIT;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.bankaccount.domain.Transaction;
import com.bankaccount.domain.Wallet;
import com.bankaccount.services.AccountService;
import com.bankaccount.services.OperationService;

public class AccountOperationTest {

	@Test
	public void initialize_account_service_test_OK() {

		final AccountService accountService = AccountService.init();

		assertThat(accountService).isEqualTo(AccountService.getLists(emptyList()));
	}

	@Test
	public void empty_account_last_operation_Test_KO() {

		final AccountService accountService = AccountService.init();

		final OperationService lastOperation = accountService.lastOperation();

		assertThat(lastOperation).isNull();
	}

	@Test
	public void update_empty_account_with_operatio_Test_OK() {
		final Transaction transaction = mock(Transaction.class);
		final OperationService expectedOperation = mock(OperationService.class);

		final AccountService accountService = AccountService.init();
		when(transaction.processOperation(any())).thenReturn(expectedOperation);

		final AccountService updatedAccountService = accountService.updateOperation(transaction);
		assertThat(updatedAccountService).isEqualTo(AccountService.getLists(singletonList(expectedOperation)));
	}

	@Test
	public void list_account_operations_Test_OK() {
		final OperationService operationService = mock(OperationService.class);
		final AccountService accountService = AccountService.getLists(singletonList(operationService));

		final List<OperationService> operationServices = accountService.getOperations();
		assertThat(operationServices).contains(operationService);
	}

	@Test
	public void update_account_with_operation_Test_OK() {
		final Transaction operation = mock(Transaction.class);
		final OperationService expectedOperationStatement = mock(OperationService.class);

		final OperationService operationService = mock(OperationService.class);
		final AccountService accountService = AccountService.getLists(singletonList(operationService));

		when(operationService.calculateNext(operation)).thenReturn(expectedOperationStatement);
		final AccountService updatedAccountStatement = accountService.updateOperation(operation);

		assertThat(updatedAccountStatement)
				.isEqualTo(AccountService.getLists(asList(operationService, expectedOperationStatement)));
	}

	@Test
	public void last_operation_test_OK() {
		final List<OperationService> operationServices = asList(
				OperationService.get(CREDIT, LocalDateTime.now(), Wallet.init(3L), TransactionService.get(3L)),
				OperationService.get(CREDIT, LocalDateTime.now(), Wallet.init(5L), TransactionService.get(8L)),
				OperationService.get(CREDIT, LocalDateTime.now(), Wallet.init(7L), TransactionService.get(15L)));
		final AccountService accountService = AccountService.getLists(operationServices);

		final OperationService lastStatement = accountService.lastOperation();

		assertThat(lastStatement).isEqualTo(operationServices.get(2));
	}

}