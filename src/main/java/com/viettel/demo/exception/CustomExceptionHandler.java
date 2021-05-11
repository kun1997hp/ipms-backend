package com.viettel.demo.exception;

import com.viettel.demo.common.message.ErrorMessage;
import com.viettel.demo.common.response.MessageResponse;
import com.viettel.demo.exception.customexception.BusinessException;
import com.viettel.demo.exception.customexception.InvalidInputException;
import com.viettel.demo.exception.customexception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ErrorMessage errorMessage;

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<MessageResponse> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        MessageResponse error = new MessageResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<MessageResponse> handleUserNotFoundException(BusinessException ex, WebRequest request) {
        MessageResponse error = new MessageResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        List<String> messages = objectErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        MessageResponse error = new MessageResponse(messages);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public final ResponseEntity<MessageResponse> handleDuplicateKeyException(DuplicateKeyException ex, WebRequest request) {
        MessageResponse error = new MessageResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        MessageResponse error = new MessageResponse(ex.getCause().getCause().getMessage());
        return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
    }

    // Handle Json body request wrong format
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String mess = errorMessage.getUnexpectedError();
        MessageResponse error = new MessageResponse(mess);
        return new ResponseEntity<>(error, status);
    }

    // Handle other exception
    // The message is not display to end user
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<MessageResponse> handleCommonException(Exception ex, WebRequest request) {
        MessageResponse error = new MessageResponse(errorMessage.getUnexpectedError());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<MessageResponse> handleInvalidInputException(InvalidInputException ex, WebRequest request) {
        MessageResponse error = new MessageResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}