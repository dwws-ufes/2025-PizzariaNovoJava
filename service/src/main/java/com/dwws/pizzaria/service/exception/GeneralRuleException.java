package com.dwws.pizzaria.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GeneralRuleException extends ResponseStatusException {

	public GeneralRuleException(String razao) {
		super(HttpStatus.BAD_REQUEST, razao);
	}

}
