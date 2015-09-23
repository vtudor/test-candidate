package com.test.candidate.service.dto;

import com.test.candidate.persistence.entity.Candidate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Tudor on 22-Sep-15.
 */
public class CandidateDto implements Serializable
{
    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    private Boolean enabled;


    //ctor used for converting from Entity to DTO (The Service layer has access to the persistence layer)
    public CandidateDto(Candidate candidate)
    {
        this.name = candidate.getName();
        this.enabled = candidate.getEnabled();
    }

    private CandidateDto()
    {
    }


    public String getName()
    {
        return name;
    }

    public Boolean getEnabled()
    {
        return enabled;
    }

}
