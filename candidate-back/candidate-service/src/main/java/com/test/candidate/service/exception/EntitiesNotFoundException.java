package com.test.candidate.service.exception;

/**
 * Created by Tudor on 23-Sep-15.
 */
public class EntitiesNotFoundException extends RuntimeException
{
    public EntitiesNotFoundException(String message)
    {
        super(message);
    }

    public EntitiesNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
