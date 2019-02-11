package org.banco.renegociacao.ws.dto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespostaPadraoDto<T> {

	private boolean valido;
	private T conteudo;
	private String codigo;
	private String mensagem;
	private List<String> motivos;
	private String status;
	@Builder.Default
	private OffsetDateTime timestamp = OffsetDateTime.now();

	public void invalidar() {
		this.valido = false;
	}

	public void addMensagem(String mensagem) {
		if (this.motivos == null) {
			this.motivos = new ArrayList<>();
		}
		this.motivos.add(mensagem);
	}

	/**
	 * Adiciona uma mensagem e inválida o status
	 *
	 * @param erro -> Erros
	 */
	public void addErro(String erro) {
		invalidar();
		addMensagem(erro);
	}

	/**
	 * Adiciona uma Lista de Mensagens de erro e invalida o status
	 *
	 * @param erros -> Erros
	 */
	public void addErros(List<String> erros) {
		invalidar();
		erros.forEach(this::addMensagem);
	}
}