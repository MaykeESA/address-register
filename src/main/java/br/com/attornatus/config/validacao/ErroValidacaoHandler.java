package br.com.attornatus.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroValidacaoHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormularioDto> handle(MethodArgumentNotValidException exception) {
		List<ErroFormularioDto> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		for(FieldError fe: fieldErrors) {
			String mensagem = this.messageSource.getMessage(fe, LocaleContextHolder.getLocale());
			ErroFormularioDto erro = new ErroFormularioDto(fe.getField(), mensagem);
			dto.add(erro);
		}
		return dto;
	}
}
