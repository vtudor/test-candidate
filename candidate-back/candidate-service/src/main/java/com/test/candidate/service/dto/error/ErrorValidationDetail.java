package com.test.candidate.service.dto.error;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tudor on 23-Sep-15.
 */
public class ErrorValidationDetail extends ErrorDetail
{
    private List<ValidationError> fieldErrors = new ArrayList<ValidationError>();

    public List<ValidationError> getFieldErrors()
    {
        return fieldErrors;
    }

}
