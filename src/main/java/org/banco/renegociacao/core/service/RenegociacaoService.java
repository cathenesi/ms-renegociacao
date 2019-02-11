package org.banco.renegociacao.core.service;

import java.util.List;

import org.banco.renegociacao.core.exception.RenegociacaoException;
import org.banco.renegociacao.ws.dto.DividaDto;
import org.banco.renegociacao.ws.dto.SimulacaoPagamentoDto;

public interface RenegociacaoService {

	public List<DividaDto> listarDividas(Long cpf) throws RenegociacaoException;

	public SimulacaoPagamentoDto simularPagamento(Long cpf) throws RenegociacaoException;
	
}
