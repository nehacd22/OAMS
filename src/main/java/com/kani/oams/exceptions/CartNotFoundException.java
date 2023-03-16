package com.kani.oams.exceptions;

public class CartNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CartNotFoundException(String exMsg) {
		super(exMsg);
	}

}
