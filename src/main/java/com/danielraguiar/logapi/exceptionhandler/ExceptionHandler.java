package com.danielraguiar.logapi.exceptionhandler;

import com.danielraguiar.logapi.domain.exception.EntityNotFoundException;
import com.danielraguiar.logapi.domain.exception.Exceptions;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Error.Campo> campos = new ArrayList<>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new Error.Campo(nome, mensagem));
        }
        Error error = new Error();
        error.setStatus(status.value());
        error.setDataHora(OffsetDateTime.now());
        error.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento corretamente e tente novamente.");
        error.setCampos(campos);
        return handleExceptionInternal(ex, "Valor i", headers, status, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exceptions.class)
    public ResponseEntity<Object> handleException(Exceptions ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Error error = new Error();
        error.setStatus(status.value());
        error.setDataHora(OffsetDateTime.now());
        error.setTitulo(ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(Exceptions ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Error error = new Error();
        error.setStatus(status.value());
        error.setDataHora(OffsetDateTime.now());
        error.setTitulo(ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
}
