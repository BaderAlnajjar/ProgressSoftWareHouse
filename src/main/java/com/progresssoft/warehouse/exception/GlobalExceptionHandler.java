package com.progresssoft.warehouse.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.progresssoft.warehouse.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.util.*;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ApiCallError<String>> handleNotFoundException(
            HttpServletRequest request,
            NotFoundException ex) {


        return ResponseEntity
                .status(NOT_FOUND)
                .body(new ApiCallError<>("Not found",
                        null,
                        WebUtils.getCurrentRequestUrl(),
                        List.of(ex.getMessage())));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ApiCallError<String>>
    handleIllegalArgumentException(HttpServletRequest request,
                                   IllegalArgumentException ex) {

        return ResponseEntity
                .badRequest()
                .body(new ApiCallError<>(
                        "Illegal Arguments",
                        null,
                        WebUtils.getCurrentRequestUrl(),
                        List.of(ex.getMessage())));
    }


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ApiCallError<String>>
    handleValidationException(HttpServletRequest request,
                              ValidationException ex) {

        return ResponseEntity
                .badRequest()
                .body(new ApiCallError<>(
                        "Validation exception",
                        null,
                        WebUtils.getCurrentRequestUrl(),
                        List.of(ex.getMessage())));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ApiCallError<String>>
    handleMissingServletRequestParameterException(HttpServletRequest request,
                                                  MissingServletRequestParameterException ex) {

        return ResponseEntity
                .badRequest()
                .body(new ApiCallError<>("Missing request parameter",
                        null,
                        WebUtils.getCurrentRequestUrl(),
                        List.of(Objects.requireNonNull(ex.getMessage()))));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ApiCallError<Map<String, String>>>
    handleMethodArgumentTypeMismatchException(HttpServletRequest request,
                                              MethodArgumentTypeMismatchException ex) {

        Map<String, String> details = new HashMap<>();
        details.put("paramName", ex.getName());
        details.put("paramValue", ex.getValue() == null ? "" : ex.getValue().toString());
        details.put("errorMessage", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(new ApiCallError<>(
                        "Method argument type mismatch",
                        null,
                        WebUtils.getCurrentRequestUrl(),
                        List.of(details)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ApiCallError<Map<String, String>>>
    handleMethodArgumentNotValidException(HttpServletRequest request,
                                          MethodArgumentNotValidException ex) {

        List<Map<String, String>> details = new ArrayList<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                    Map<String, String> detail = new HashMap<>();
                    detail.put("objectName", fieldError.getObjectName());
                    detail.put("field", fieldError.getField());
                    detail.put("rejectedValue", String.valueOf(fieldError.getRejectedValue()));
                    detail.put("errorMessage", fieldError.getDefaultMessage());
                    details.add(detail);
                });

        return ResponseEntity
                .badRequest()
                .body(new ApiCallError<>(
                        "Method argument validation failed",
                        null,
                        WebUtils.getCurrentRequestUrl(),
                        details));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(FORBIDDEN)
    public ResponseEntity<ApiCallError<String>>
    handleAccessDeniedException(HttpServletRequest request, AccessDeniedException ex) {

        return ResponseEntity
                .status(FORBIDDEN)
                .body(new ApiCallError<>("Access denied!",
                        null,
                        WebUtils.getCurrentRequestUrl(),
                        List.of(ex.getMessage())));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ResponseEntity<ApiCallError<String>>
    handleUnauthorizedException(HttpServletRequest request, HttpClientErrorException ex) {

        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(new ApiCallError<>("Unauthorized Access, check your credentials!",
                        null,
                        WebUtils.getCurrentRequestUrl(),
                        List.of(ex.getMessage() != null ? ex.getMessage() : "")));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiCallError<String>>
    handleInternalServerError(HttpServletRequest request, Exception ex) {

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(new ApiCallError<>(
                        "Internal server error",
                        null,
                        WebUtils.getCurrentRequestUrl(),
                        List.of(ex.getMessage())));
    }

    /**
     * handle {@link ProgressoftException} exception.
     *
     * @param ex exception instance
     * @return obie API response represent the given exception
     */
    @ExceptionHandler(ProgressoftException.class)
    public ResponseEntity<ApiCallError<String>> handleEnergiTechException(HttpServletRequest request, ProgressoftException ex) {


        return ResponseEntity
                .status(ex.getErrorCategory().getHttpStatus())
                .body(new ApiCallError<>(
                        ex.getBusinessErrorCode().getErrorMessage(),
                        ex.getBusinessErrorCode().getCode(),
                        WebUtils.getCurrentRequestUrl(),
                        List.of(ex.getMessage())));
    }
}

@JsonRootName("ApiError")
record ApiCallError<T>(
        @JsonProperty("message") String message,
        @JsonProperty("errorCode") String errorCode,
        @JsonProperty("url") String url,
        @JsonProperty("details") List<T> details) {
}
