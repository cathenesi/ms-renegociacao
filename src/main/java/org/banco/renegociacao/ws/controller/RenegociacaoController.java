package org.banco.renegociacao.ws.controller;

import java.util.List;

import org.banco.renegociacao.core.exception.RenegociacaoException;
import org.banco.renegociacao.core.service.RenegociacaoService;
import org.banco.renegociacao.ws.contract.RenegociacaoWebService;
import org.banco.renegociacao.ws.dto.DividaDto;
import org.banco.renegociacao.ws.dto.SimulacaoPagamentoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class RenegociacaoController implements RenegociacaoWebService {

	@Autowired
	private RenegociacaoService renegociacaoService;

	@Override
	public List<DividaDto> listarDividas(@PathVariable(name = "cpf", required = true) Long cpf) throws RenegociacaoException {

		try {
			log.info(">> cpf: " + cpf);
			return this.renegociacaoService.listarDividas(cpf);
		} catch (RuntimeException e) {
			log.error("Erro ao listar dívidas", e);
			throw e;
		}
	}

	@Override
	public SimulacaoPagamentoDto simularPagamento(@PathVariable(name = "cpf", required = true) Long cpf) throws RenegociacaoException {

		try {
			log.info(">> cpf: " + cpf);
			return this.renegociacaoService.simularPagamento(cpf);
		} catch (RuntimeException e) {
			log.error("Erro ao simular pagamento", e);
			throw e;
		}
	}

}
