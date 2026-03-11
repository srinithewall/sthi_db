package com.sthi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${spring.servlet.multipart.max-file-size:10MB}")
    private String maxFileSize;

    // =====================================================
    // 400 - IllegalArgumentException (Business validation)
    // =====================================================
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        logger.warn("Illegal argument: {}", ex.getMessage());

        return buildStandardResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getRequestURI());
    }

    // =====================================================
    // 400 - IllegalStateException (Business rule violation)
    // =====================================================
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalStateException(
            IllegalStateException ex,
            HttpServletRequest request) {

        logger.warn("Illegal state: {}", ex.getMessage());

        return buildStandardResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(org.springframework.web.bind.MissingPathVariableException.class)
    public ResponseEntity<Object> handleMissingPathVariable(
            Exception ex,
            HttpServletRequest request) {

        return buildStandardResponse(
                HttpStatus.BAD_REQUEST,
                "Required path variable is missing.",
                request.getRequestURI());
    }

    @ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(
            Exception ex,
            HttpServletRequest request) {

        return buildStandardResponse(
                HttpStatus.BAD_REQUEST,
                "Invalid property ID format.",
                request.getRequestURI());
    }

    // =====================================================
    // 400 - DTO Validation Errors (@Valid)
    // =====================================================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        logger.warn("Validation failed");

        Map<String, Object> body = buildBaseBody(
                HttpStatus.BAD_REQUEST,
                request.getRequestURI());

        body.put("error", "Validation Failed");
        body.put("message", "Validation failed for one or more fields");

        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // =====================================================
    // 413 - File Too Large
    // =====================================================
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxSizeException(
            MaxUploadSizeExceededException ex,
            HttpServletRequest request) {

        logger.warn("Max upload size exceeded");

        return buildStandardResponse(
                HttpStatus.PAYLOAD_TOO_LARGE,
                "Maximum upload size exceeded. Max allowed: " + maxFileSize,
                request.getRequestURI());
    }

    // =====================================================
    // 400 - Multipart Errors
    // =====================================================
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Object> handleMultipartException(
            MultipartException ex,
            HttpServletRequest request) {

        logger.error("Multipart error", ex);

        return buildStandardResponse(
                HttpStatus.BAD_REQUEST,
                "Invalid multipart request.",
                request.getRequestURI());
    }

    // =====================================================
    // 500 - Global Fallback (Unexpected Errors)
    // =====================================================

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Duplicate Entry");
        response.put("message", "This tag is already assigned to the property.");
        response.put("path", request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(
            Exception ex,
            HttpServletRequest request) {

        logger.error("Unhandled exception for request " + request.getRequestURI(), ex);

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Internal Server Error");
        response.put("message", "Something went wrong. Please try again later.");
        response.put("debug_message", ex.getMessage());
        response.put("debug_exception", ex.getClass().getName());

        // To see print trace if needed
        // java.io.StringWriter sw = new java.io.StringWriter();
        // ex.printStackTrace(new java.io.PrintWriter(sw));
        // response.put("debug_trace", sw.toString());

        response.put("path", request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request) {

        logger.warn("Business exception: {}", ex.getMessage());

        return buildStandardResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getRequestURI());
    }

    // =====================================================
    // Helper: Standard Response Builder
    // =====================================================
    private ResponseEntity<Object> buildStandardResponse(
            HttpStatus status,
            String message,
            String path) {

        Map<String, Object> body = buildBaseBody(status, path);

        body.put("error", status.getReasonPhrase());
        body.put("message", message);

        return new ResponseEntity<>(body, status);
    }

    // =====================================================
    // Helper: Base Response Structure
    // =====================================================
    private Map<String, Object> buildBaseBody(
            HttpStatus status,
            String path) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("path", path);

        return body;
    }
}