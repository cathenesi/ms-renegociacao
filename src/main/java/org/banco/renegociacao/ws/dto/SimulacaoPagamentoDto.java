package org.banco.renegociacao.ws.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.banco.renegociacao.core.model.Renegociacao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Simulação para renegociação de divida")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimulacaoPagamentoDto {

	@ApiModelProperty(position = 1, value = "Valor soma das dívidas")
	@JsonProperty("valor-dividas")
	private BigDecimal valorDividas;

	@ApiModelProperty(position = 2, value = "Valor total - soma das dívidas")
	@JsonProperty("valor-juros")
	private BigDecimal valorJuros;

	@ApiModelProperty(position = 3, value = "Data do vencimento da primeira parcela")
	@JsonProperty("data-vencimento-primeira-parcela")
	private Date dataVencimentoPrimeiraParcela;

	@ApiModelProperty(position = 4, value = "Valor de cada parcela")
	@JsonProperty("valor-parcela")
	private BigDecimal valorParcela;

	@ApiModelProperty(position = 5, value = "Número de parcelas")
	@JsonProperty("numero-parcelas")
	private int numeroParcelas;

	public SimulacaoPagamentoDto(Renegociacao renegociacao) {
		
		if (renegociacao == null) {
			return;
		}
		this.valorDividas = renegociacao.getValorDividas();
		this.valorJuros = renegociacao.getValorJuros();
		this.dataVencimentoPrimeiraParcela = renegociacao.getDataVencimentoPrimeiraParcela();
		this.valorParcela = renegociacao.getValorParcela();
		this.numeroParcelas = renegociacao.getNumeroParcelas();
	}

}
