package com.bankaccount.services;

import java.util.Objects;

import com.bankaccount.domain.Wallet;

public class TransactionService {

	private final long wallet;

	private TransactionService(final long wallet) {
		this.wallet = wallet;
	}

	static TransactionService init() {
		return new TransactionService(0L);
	}

	static TransactionService get(final long euro) {
		return new TransactionService(euro);
	}

	public TransactionService credit(final Wallet pwallet) {
		return new TransactionService(pwallet.creditMoneyInEuro(wallet));
	}

	public TransactionService debit(final Wallet pWallet) {
		return new TransactionService(pWallet.debitMoneyInEuro(wallet));
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final TransactionService accountBalance = (TransactionService) o;
		return wallet == accountBalance.wallet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(wallet);
	}

	@Override
	public String toString() {
		return "Total Account in € : " + wallet + "e.";

	}
}
