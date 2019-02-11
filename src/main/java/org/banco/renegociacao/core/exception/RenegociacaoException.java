package org.banco.renegociacao.core.exception;

import org.banco.renegociacao.core.util.BusinessException;

public class RenegociacaoException extends BusinessException {

	private static final long serialVersionUID = -1184273812015346185L;

	public RenegociacaoException(String errorCode) {
		super(errorCode);
	}

}
