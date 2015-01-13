package com.ellisdon.portal.mv.exception;

public class MyVacationRuntimeException extends RuntimeException {
	public MyVacationRuntimeException(String errorMessage,Throwable cause) {
        super(errorMessage,cause);        
    }	
}
