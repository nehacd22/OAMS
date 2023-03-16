package com.kani.oams.exceptions;

public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String exMsg) {
		super(exMsg);
	}

}
