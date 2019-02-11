package org.banco.renegociacao.ws.contract;

import java.net.HttpURLConnection;
import java.util.List;

import org.banco.renegociacao.core.exception.RenegociacaoException;
import org.banco.renegociacao.ws.dto.DividaDto;
import org.banco.renegociacao.ws.dto.SimulacaoPagamentoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "MS Renegociação")
@RequestMapping("/renegociacao")
public interface RenegociacaoWebService {

	@ApiOperation(notes = "Lista as dívidas de um CPF", value = "Lista dívidas")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Retorna dívidas"),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad Request"), 
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized")
	})
    @GetMapping("/dividas/{cpf}/listar")
	List<DividaDto> listarDividas(@ApiParam(value = "Número do CPF do cliente", required = true) Long cpf) throws RenegociacaoException;

	@ApiOperation(notes = "Simula pagamento para as dívidas de um CPF", value = "Simula pagamento de dívidas")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Retorna proposta de pagamento para as dívidas"),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad Request"), 
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized")
	})
    @GetMapping("/dividas/{cpf}/simular")
	SimulacaoPagamentoDto simularPagamento(@ApiParam(value = "Número do CPF do cliente", required = true) Long cpf) throws RenegociacaoException;

}
