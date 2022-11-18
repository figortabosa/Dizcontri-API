package com.exemple.dizcontre.api.exceptionHandle;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DiscontreExceptionHandle extends ResponseEntityExceptionHandler{

	@Autowired
	MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagenUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause().toString();
		
		return handleExceptionInternal(ex, new Erro(mensagenUsuario, mensagemDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(Exception ex) {
		
		String mensagenUsuario = "Cpf repetido";
		String mensagemDesenvolvedor = ex.getCause().getCause().toString();
		
        return new ResponseEntity<Object>(new Erro(mensagenUsuario,mensagemDesenvolvedor), HttpStatus.BAD_REQUEST);
    }
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-
		
		List<Erro> erros = criarListaErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Erro> criarListaErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
		String mensagenUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = fieldError.toString();
		erros.add(new Erro(mensagenUsuario, mensagemDesenvolvedor));
		
		}
		return erros;
	}
	
	
	
	public static class Erro {
		
		private String mensagenUsuario;
		private String mensagemDesenvolvedor;
		public Erro(String mensagenUsuario, String mensagemDesenvolvedor) {
			super();
			this.mensagenUsuario = mensagenUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}
		public String getMensagenUsuario() {
			return mensagenUsuario;
		}
		public void setMensagenUsuario(String mensagenUsuario) {
			this.mensagenUsuario = mensagenUsuario;
		}
		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
		public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}
		
		
	}
}
