package com.dwws.pizzaria.service.exception;

public class EntityNotFoundException extends RuntimeException {

	public EntityNotFoundException(String razao) {
		super(razao);
	}

}
