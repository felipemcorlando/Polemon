package com.unicamp.br.mc322.polemon;

public class IllegalValueException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalValueException () {
		super();		
	}
	
	public IllegalValueException (String message) {
		super(message);
	}
	
	public IllegalValueException (Throwable cause) {
		super(cause);
	}
	
	public IllegalValueException (String message, Throwable cause) {
		super(message,cause);	
	}
	
}
