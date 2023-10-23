package com.danielraguiar.logapi.exceptionhandler;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Error error = new Error();
        List<Error.Campo> campos = ex.getBindingResult().getAllErrors().stream()
                .filter(errors -> errors instanceof FieldError)
                .map(errors -> {
                    FieldError fieldError = (FieldError) errors;
                    String nome = fieldError.getField();
                    String mensagem = messageSource.getMessage(errors, LocaleContextHolder.getLocale());
                    return new Error.Campo(nome, mensagem);
                })
                .collect(Collectors.toList());        error.setStatus(status.value());
        error.setDataHora(LocalDateTime.now());
        error.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento corretamente e tente novamente.");
        error.setCampos(campos);
        return handleExceptionInternal(ex, "Valor i", headers, status, request);
    }
}
