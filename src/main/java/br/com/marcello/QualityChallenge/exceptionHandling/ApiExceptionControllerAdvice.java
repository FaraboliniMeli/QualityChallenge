package br.com.marcello.QualityChallenge.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionResponse> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getAllErrors()
                .forEach(objectError -> sb.append(objectError.getDefaultMessage() + ". "));

        ApiExceptionResponse response = new ApiExceptionResponse();
        response.setDate(LocalDate.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setExceptionMessage(sb.toString());
        response.setMessage("Invalid JSON format, please check doc.");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> globalExceptionHandler(Exception e) {

        ApiExceptionResponse response = new ApiExceptionResponse();
        response.setDate(LocalDate.now());
        response.setExceptionMessage(e.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Internal Server Error. Please read exception message.");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiExceptionResponse> noSuchElementHandler(NoSuchElementException e) {

        ApiExceptionResponse response = new ApiExceptionResponse();
        response.setDate(LocalDate.now());
        response.setExceptionMessage(e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage("District not found in repository. Please check available districts");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
