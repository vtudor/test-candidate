package com.test.candidate.service.controller.advice;

import com.test.candidate.service.dto.error.ErrorDetail;
import com.test.candidate.service.dto.error.ErrorValidationDetail;
import com.test.candidate.service.dto.error.ValidationError;
import com.test.candidate.service.exception.EntitiesNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Tudor on 23-Sep-15.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDetail handleResourceNotFoundException(Exception e)
    {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setType(e.getClass().getSimpleName());
        errorDetail.setMessage("Resource not found");

        return errorDetail;
    }

    @ExceptionHandler(EntitiesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDetail handleResourcesNotFoundException(Exception e)
    {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setType(e.getClass().getSimpleName());
        errorDetail.setMessage(e.getMessage());

        return errorDetail;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDetail handleGenericException(EntityNotFoundException e)
    {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setType(e.getClass().getSimpleName());
        errorDetail.setMessage("Internal exception. Please contact administrator admin@localhost");

        return errorDetail;
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request)
    {

        ErrorValidationDetail errorValidationDetail = new ErrorValidationDetail();
        errorValidationDetail.setType(e.getClass().getSimpleName());
        errorValidationDetail.setMessage("Input validation failed");

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fe : fieldErrors)
        {

            List<ValidationError> validationErrorList = errorValidationDetail.getFieldErrors();
            ValidationError validationError = new ValidationError();
            validationError.setResource(getResourceName(request));
            validationError.setField(fe.getField());
            validationError.setCode(fe.getCode());
            validationError.setRejectedValue(fe.getRejectedValue());
            validationErrorList.add(validationError);
        }

        return handleExceptionInternal(e, errorValidationDetail, headers, status, request);
    }


    private String getResourceName(WebRequest request)
    {
        String[] uriPathSplit = request.getDescription(false).split("/");
        return uriPathSplit[uriPathSplit.length - 1];
    }

}
