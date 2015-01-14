package com.joantolos.spring.mvc.common.exception;

import com.joantolos.spring.mvc.common.enums.DAOErrorCode;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorParameter = null;
	private DAOErrorCode errorCode = DAOErrorCode.OK;

	public DAOException() {

	}

	public DAOException(String message) {
		super(message);

	}
	public DAOException(DAOErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public DAOException(DAOErrorCode errorCode, String errorParameter) {
		this.errorCode = errorCode;
		this.errorParameter = errorParameter;
	}
		
	public DAOErrorCode getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(DAOErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public String errorMessage() {
		switch (errorCode) {
			case OK:
				return "TILT: Should not get here.";
            case SQL_EXCEPTION:
                return String.format("Sql exception: "+this.getMessage());
		}
		return "";
	}
}

