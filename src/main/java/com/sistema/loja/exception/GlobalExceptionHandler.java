package com.sistema.loja.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ProblemDetail> handleNotFound(
            RecursoNaoEncontradoException ex, HttpServletRequest req) {

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Recurso não encontrado");
        pd.setInstance(URI.create(req.getRequestURI()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pd);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ProblemDetail> handleBusiness(
            RegraDeNegocioException ex, HttpServletRequest req) {

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        pd.setTitle("Erro de negócio");
        pd.setInstance(URI.create(req.getRequestURI()));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(pd);
    }

    // Captura QUALQUER outra exceção e loga o erro real
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGeneric(
            Exception ex, HttpServletRequest req) {

        // Este log vai mostrar o erro real nos logs do container
        log.error("Erro interno em {}: {}", req.getRequestURI(), ex.getMessage(), ex);

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno: " + ex.getMessage());
        pd.setTitle("Erro interno do servidor");
        pd.setInstance(URI.create(req.getRequestURI()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(pd);
    }
}