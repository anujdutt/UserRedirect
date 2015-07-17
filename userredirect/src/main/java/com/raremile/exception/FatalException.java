package com.raremile.exception;

/**
 * Critical Exception
 *
 */
public class FatalException extends RuntimeException {

	/**
	 * default serial version id
	 */
	private static final long serialVersionUID = 1L;

	public FatalException() {
		super();
	}

	public FatalException(String message) {
		super(message);
	}

	public FatalException(String message, Throwable thrw) {
		super(message, thrw);
	}
}
