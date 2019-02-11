package org.banco.renegociacao.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.banco.renegociacao.core.exception.RenegociacaoException;
import org.banco.renegociacao.core.model.DividaCliente;
import org.banco.renegociacao.core.model.Renegociacao;
import org.banco.renegociacao.core.service.RenegociacaoService;
import org.banco.renegociacao.infrastructure.repository.DividaClienteRepository;
import org.banco.renegociacao.ws.dto.DividaDto;
import org.banco.renegociacao.ws.dto.SimulacaoPagamentoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RenegociacaoServiceImpl implements RenegociacaoService {

	@Autowired
	private DividaClienteRepository repository;

	@Override
	public List<DividaDto> listarDividas(Long cpf) throws RenegociacaoException {

		List<DividaDto> result = new ArrayList<>();

		DividaCliente dividaCliente = this.repository.findById(cpf).get();
		
		if (dividaCliente != null && dividaCliente.getDividas() != null) {
			dividaCliente.getDividas().forEach(
					d -> result.add(new DividaDto(d.getDataVencimento(), d.getValor())));
		}

		return result;
	}

	@Override
	public SimulacaoPagamentoDto simularPagamento(Long cpf) throws RenegociacaoException {

		return new SimulacaoPagamentoDto(new Renegociacao(this.repository.findById(cpf).get()));
	}

}
