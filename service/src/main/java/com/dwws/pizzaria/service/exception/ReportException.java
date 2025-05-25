package com.dwws.pizzaria.service.exception;

public class ReportException extends RuntimeException {

	public ReportException(final String mensagem) {
		this(mensagem, null);
	}

	public ReportException(final String mensagem, final Throwable causa) {
		super(mensagem, causa);
	}
}
