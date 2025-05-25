package com.dwws.pizzaria.service.exception;

public class BusinessRuleException extends RuntimeException {

	public BusinessRuleException(final String mensagem) {
		this(mensagem, null);
	}

	public BusinessRuleException(final String mensagem, final Throwable causa) {
		super(mensagem, causa);
	}
}
