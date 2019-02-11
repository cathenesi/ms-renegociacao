package org.banco.renegociacao.ws.exception.handler;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.banco.renegociacao.core.util.BusinessException;
import org.banco.renegociacao.core.util.Message;
import org.banco.renegociacao.core.util.helper.MessageHelper;
import org.banco.renegociacao.ws.dto.RespostaPadraoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

	@Autowired
	private Message message;

	@Autowired
	private ErrorAttributes errorAttributes;

	/**
	 * Tratamento padrão para exceções de negócio
	 * 
	 * @param request
	 * @param webRequest
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { Exception.class })
	public RespostaPadraoDto<Void> exception(HttpServletRequest request, WebRequest webRequest, Exception ex) {
		return getRespostaPadrao(request, ex, obterCodigoErro(ex), obterListaDeErros(ex, webRequest));
	}

	/**
	 * Tratamento padrão para exceções técnicas
	 * 
	 * @param request
	 * @param webRequest
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	public RespostaPadraoDto<Void> methodArgumentTypeMismatchException(HttpServletRequest request,
			WebRequest webRequest, Exception ex) {
		RespostaPadraoDto<Void> erroDTO = getRespostaPadrao(request, ex, obterCodigoErro(ex),
				obterListaDeErros(ex, webRequest));
		return erroDTO;
	}

	/**
	 * Extrai uma lista de mensagens padrões do springboot
	 *
	 * @param ex
	 * @param request
	 * @return
	 */
	private List<String> obterListaDeErros(Exception ex, WebRequest request) {

		if (ex instanceof BusinessException) {
			return ((BusinessException) ex).getReasons();
		}

		Set<Map.Entry<String, Object>> values = errorAttributes.getErrorAttributes(request, true).entrySet();
		List<String> erros = new ArrayList<>();
		values.forEach(o -> {
			if ("message".equalsIgnoreCase(o.getKey())) {
				erros.add(String.valueOf(o.getValue()));
			}
		});
		return erros;
	}

	/**
	 * Retorna codigo de erro
	 *
	 * @param ex
	 * @return
	 */
	private String obterCodigoErro(Exception ex) {

		if (ex instanceof BusinessException) {
			return ((BusinessException) ex).getErrorCode();
		}
		return MessageHelper.ERRO_APLICACAO;
	}

	/**
	 * Constroi uma resposta padrão e loga o erro.
	 *
	 * @param request
	 * @param ex
	 * @param codigoErro
	 * @param motivos
	 * @return
	 */
	private RespostaPadraoDto<Void> getRespostaPadrao(HttpServletRequest request, Exception ex, String codigoErro,
			List<String> motivos) {

		String url = request.getRequestURL().toString();

		String mensagemCodigoErro = this.message
				.getMessage(codigoErro == null ? MessageHelper.ERRO_APLICACAO_NEGOCIO : codigoErro);

		RespostaPadraoDto<Void> erroDTO = RespostaPadraoDto.<Void>builder().valido(false).mensagem(mensagemCodigoErro)
				.codigo(codigoErro).motivos(motivos).build();

		StringBuilder logBuilder = new StringBuilder();
		logBuilder.append("ERRO: ").append(erroDTO.toString());
		logBuilder.append(", url: ").append(url);
		logBuilder.append(", params: [");
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = params.nextElement();
			logBuilder.append(" ").append(paramName).append("=").append(request.getParameter(paramName));
		}
		logBuilder.append(" ]");

		log.info(logBuilder.toString());
		if (!(ex instanceof BusinessException)) {
			log.error(logBuilder.toString(), ex);
		}
		return erroDTO;
	}

}
