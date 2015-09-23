package com.test.candidate.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tudor on 23-Sep-15.
 */
public class IdsDto
{
    @NotNull
    @Size(min = 1)
    private List<Integer> ids = new ArrayList<Integer>();

    public List<Integer> getIds()
    {
        return ids;
    }

    public void setIds(List<Integer> ids)
    {
        this.ids = ids;
    }
}
