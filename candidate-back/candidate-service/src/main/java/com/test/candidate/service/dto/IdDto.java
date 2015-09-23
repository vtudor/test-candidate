package com.test.candidate.service.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Tudor on 23-Sep-15.
 */
public class IdDto
{
    @NotNull
    private Integer id;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}