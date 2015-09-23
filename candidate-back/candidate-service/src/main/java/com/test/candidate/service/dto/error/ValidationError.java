package com.test.candidate.service.dto.error;

/**
 * Created by Tudor on 23-Sep-15.
 */
public class ValidationError
{
    private String resource;
    private String field;
    private String code;
    private Object rejectedValue;

    public String getResource()
    {
        return resource;
    }

    public void setResource(String resource)
    {
        this.resource = resource;
    }

    public String getField()
    {
        return field;
    }

    public void setField(String field)
    {
        this.field = field;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Object getRejectedValue()
    {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue)
    {
        this.rejectedValue = rejectedValue;
    }
}
