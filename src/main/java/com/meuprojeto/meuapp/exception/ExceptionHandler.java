package com.meuprojeto.meuapp.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Autowired
    private MessageSource messageSource;

    // @Override
    // protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
    //         HttpHeaders headers, HttpStatus status, WebRequest request) {

    //     String mensagemUsuario= messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
    //     String mensagemDesenvolvedor = ex.getCause().toString();
        
    //     return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
    // }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

            List<Erro> erros = criarListaDeErros(ex.getBindingResult());

            return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> criarListaDeErros (BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<Erro>();

        for (ObjectError fieldError : bindingResult.getAllErrors()) {
            String nomeCampo =  ((FieldError)fieldError).getField();
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            erros.add(new Erro(nomeCampo, mensagem));
        }
        return erros;
    }


    
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Erro {
        private String nomeCampo;
        private String mensagem;
    }

}
