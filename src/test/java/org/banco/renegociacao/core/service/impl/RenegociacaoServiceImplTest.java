package org.banco.renegociacao.core.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.banco.renegociacao.core.model.Divida;
import org.banco.renegociacao.core.model.DividaCliente;
import org.banco.renegociacao.infrastructure.repository.DividaClienteRepository;
import org.banco.renegociacao.ws.dto.DividaDto;
import org.banco.renegociacao.ws.dto.SimulacaoPagamentoDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RenegociacaoServiceImplTest {

	private static final Long CPF = 25429106852L;
	
	@Mock
	private DividaClienteRepository repository;
	
	@InjectMocks
	private RenegociacaoServiceImpl renegociacaoServiceImpl;

	@Before
	public void prepare() {

		MockitoAnnotations.initMocks(this);

		List<Divida> dividas = new ArrayList<>();
		
		dividas.add(new Divida(Date.from(
				LocalDate.now().minus(3, ChronoUnit.MONTHS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
				BigDecimal.valueOf(1200)));
		
		dividas.add(new Divida(Date.from(
				LocalDate.now().minus(6, ChronoUnit.MONTHS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
				BigDecimal.valueOf(560)));

		Mockito.when(this.repository.findById(CPF)).thenReturn(Optional.of(new DividaCliente(CPF, dividas)) );

	}
	
	@Test
	public void testarListagemDividas() {

		List<DividaDto> result = this.renegociacaoServiceImpl.listarDividas(CPF);
		
		assert(result.size() == 2);		
	}
	
	@Test
	public void testarSimulacaoPagamento() {
		
		SimulacaoPagamentoDto result = this.renegociacaoServiceImpl.simularPagamento(CPF);
		
		assert(result.getValorDividas().equals(BigDecimal.valueOf(1760)));
		assert(result.getValorJuros().equals(BigDecimal.valueOf(176.0)));
		assert(result.getValorParcela().equals(BigDecimal.valueOf(193.6)));
		assert(result.getNumeroParcelas() == 10);
	}
	
}
