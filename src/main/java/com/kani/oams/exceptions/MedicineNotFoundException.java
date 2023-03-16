package com.kani.oams.exceptions;

public class MedicineNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MedicineNotFoundException(String exMsg) {
		super(exMsg);
	}

}
