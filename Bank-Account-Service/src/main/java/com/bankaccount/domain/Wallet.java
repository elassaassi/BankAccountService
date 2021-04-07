package com.bankaccount.domain;

import java.util.Objects;

public class Wallet {

	private final long operationAmount;

	private Wallet(final long pAmount) {
		this.operationAmount = pAmount;
	}

	public static Wallet init(final long pAmount) {
		if (pAmount <= 0)
			throw new IllegalArgumentException("Invalid Operation Amount Format");

		return new Wallet(pAmount);
	}

	public long creditMoneyInEuro(final long pAmount) {
		return operationAmount + pAmount;
	}

	public long debitMoneyInEuro(final long pAmount) {
		return pAmount - operationAmount;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Wallet wallet = (Wallet) o;
		return operationAmount == wallet.operationAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(operationAmount);
	}

	@Override
	public String toString() {
		return "Operation Amounts Wallet : " + operationAmount +"e.";
	}
}
