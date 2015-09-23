package com.test.candidate.persistence.entity;

import javax.persistence.*;

/**
 * Created by oleg on 09/08/15.
 */
@Entity
public class Candidate
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column     //should be in DB NonNull but is not
    private String name;

    @Column
    private Boolean enabled;

    //constructor used for converting from DTO; since using a layered approach, didn't add the convertToEntity()/convertToDto() methods to avoid bi-directional dependencies
    //could use a service-level Mapper that does the conversion
    public Candidate(String name, Boolean enabled)
    {
        this.name = name;
        this.enabled = enabled;
    }

    public Candidate()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getEnabled()
    {
        return enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    //we only use session attached entities, don't use collection processing => not overriding equals() & hashCode()
    //we don't have logging (for now) => don't override toString()
}
