package com.venta.sistema_pantallas.business.api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoPantallaNoEncontradoException.class)
    public ResponseEntity<ApiErrorDto> manejarProductoNoEncontrado(
            ProductoPantallaNoEncontradoException ex,
            HttpServletRequest request) {

        ApiErrorDto error = new ApiErrorDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(SkuDuplicadoException.class)
    public ResponseEntity<ApiErrorDto> manejarSkuDuplicado(
            SkuDuplicadoException ex,
            HttpServletRequest request) {

        ApiErrorDto error = new ApiErrorDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<ApiErrorDto> manejarClienteNoEncontrado(
            ClienteNoEncontradoException ex,
            HttpServletRequest request) {

        ApiErrorDto error = new ApiErrorDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({DniRucDuplicadoException.class, RazonSocialDuplicadaException.class})
    public ResponseEntity<ApiErrorDto> manejarClienteDuplicado(
            RuntimeException ex,
            HttpServletRequest request) {

        ApiErrorDto error = new ApiErrorDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDto> manejarValidaciones(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> detalles = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            detalles.put(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            );
        }

        ApiErrorDto error = new ApiErrorDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Los datos enviados no son válidos",
                request.getRequestURI(),
                detalles
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorDto> manejarJsonInvalido(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {

        ApiErrorDto error = new ApiErrorDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "El JSON contiene datos inválidos o tiene un formato incorrecto",
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> manejarErrorGeneral(
            Exception ex,
            HttpServletRequest request) {

        log.error("Error inesperado en {}", request.getRequestURI(), ex);

        ApiErrorDto error = new ApiErrorDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Ocurrió un error interno en el servidor",
                request.getRequestURI(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}