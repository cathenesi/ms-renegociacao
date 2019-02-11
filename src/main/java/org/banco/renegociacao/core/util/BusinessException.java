package org.banco.renegociacao.core.util;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public abstract class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 958617055920861777L;
	
	private String errorCode;
	private final List<String> reasons;

	public BusinessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.reasons = null;
	}

	public BusinessException(String errorCode, String... reasons) {
		super();
		this.errorCode = errorCode;
		this.reasons = reasons != null ? Arrays.asList(reasons) : null;
	}

	public BusinessException(String errorCode) {
		super();
		this.errorCode = errorCode;
		this.reasons = null;
	}

}
