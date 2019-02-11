package org.banco.renegociacao.core.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import lombok.Getter;

/**
 * Encapsula lógica para cálculo de juros e quantidades de parcelas para
 * renegociação de dívidas.
 * 
 * Deve ser refatorado para usar um pattern como Strategy, Factory, para compor
 * o cálculo conforme regras de negócio.
 * 
 * O intuito desse código é apenas demonstrar a arquitetura, não será
 * aprofundada questão de estrutura de cálculo.
 */
@Getter
public class Renegociacao {

	private BigDecimal valorDividas = BigDecimal.ZERO;

	private Date dataVencimentoPrimeiraParcela;

	private BigDecimal valorParcela;

	private BigDecimal valorJuros;

	private int numeroParcelas;

	// regra fictícia para cálculo da renegociação
	public Renegociacao(DividaCliente dividaCliente) {
		super();

		if (dividaCliente == null) {
			return;
		}
		
		this.numeroParcelas = 10;
		
		if (dividaCliente != null && dividaCliente.getDividas() != null) {
			dividaCliente.getDividas().forEach(d -> this.valorDividas = this.valorDividas.add(d.getValor()));
		}

		// calcula 10% de juros
		this.valorJuros = this.valorDividas.multiply(BigDecimal.valueOf(0.1));

		// primeira parcela para daqui um mês
		this.dataVencimentoPrimeiraParcela = Date.from(
				LocalDate.now().plus(1, ChronoUnit.MONTHS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

		// valor da parcela
		this.valorParcela = (this.valorDividas.add(this.valorJuros)).divide(BigDecimal.valueOf(this.numeroParcelas));
	}

}
