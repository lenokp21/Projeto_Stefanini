package com.pjstefanini.exception;

public class SistemaException extends Exception {

	public SistemaException(String mensagem) {
		super(mensagem);
	}
	
	public SistemaException(String mensagem, Throwable e) {
		super(mensagem, e);
	}
	
	
}
