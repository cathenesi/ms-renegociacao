package org.banco.renegociacao.ws.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Registro de dívida")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DividaDto {

	@ApiModelProperty(position = 1, value = "Data do vencimento da dívida")
	@JsonProperty("data-vencimento")
	private Date dataVencimento;

	@ApiModelProperty(position = 2, value = "Valor da dívida")
	@JsonProperty("valor")
	private BigDecimal valor;

}
