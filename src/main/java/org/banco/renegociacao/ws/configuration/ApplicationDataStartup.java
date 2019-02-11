package org.banco.renegociacao.ws.configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.banco.renegociacao.core.model.Divida;
import org.banco.renegociacao.core.model.DividaCliente;
import org.banco.renegociacao.infrastructure.repository.DividaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Cadastra dados para teste após inicializar a aplicação
 */
@Slf4j
@Component
public class ApplicationDataStartup implements ApplicationListener<ApplicationStartedEvent> {

	@Autowired
	private DividaClienteRepository repository;

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		this.inicializar();
	}

	private void inicializar() {

		List<Divida> dividas = new ArrayList<>();
		
		dividas.add(new Divida(Date.from(
				LocalDate.now().minus(3, ChronoUnit.MONTHS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
				BigDecimal.valueOf(1200)));
		
		dividas.add(new Divida(Date.from(
				LocalDate.now().minus(6, ChronoUnit.MONTHS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
				BigDecimal.valueOf(560)));

		DividaCliente dividaCliente = new DividaCliente(25429106852L, dividas);

		this.repository.save(dividaCliente);

		log.info(">> Dados de teste cadastrados");
	}

}
