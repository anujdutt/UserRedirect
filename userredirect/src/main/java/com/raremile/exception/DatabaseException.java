package com.raremile.exception;



public class DatabaseException extends FatalException {

	/**
	 * default serial version id
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseException() {
		super();
	}

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(String message, Throwable thrw) {
		super(message, thrw);
	}
}

