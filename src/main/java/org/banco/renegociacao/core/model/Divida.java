package org.banco.renegociacao.core.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Um registro de dívida de um cliente, identificado pelo seu CPF
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Divida {

	private Date dataVencimento;

	private BigDecimal valor;

}
