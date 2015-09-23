package com.test.candidate.service.controller;

import com.test.candidate.service.CandidateService;
import com.test.candidate.service.dto.CandidateDto;
import com.test.candidate.service.dto.IdDto;
import com.test.candidate.service.dto.IdsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

/**
 * Created by oleg on 12/08/15.
 */
@RestController
@RequestMapping("/candidate")
public class CandidateController
{
    @Autowired
    private CandidateService candidateService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CandidateDto getCandidate(@PathVariable("id") Integer id)    //alternatively we could use ResponseEntity
    {
        return candidateService.getCandidate(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CandidateDto> getCandidates()
    {
        return candidateService.getCandidates();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void modifyCandidate(@PathVariable("id") Integer id, @Valid @RequestBody CandidateDto dto)
    {
        candidateService.modifyCandidate(id, dto);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto createCandidate(@Valid @RequestBody CandidateDto dto)
    {
        return candidateService.createCandidate(dto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCandidates(@RequestBody @Valid IdsDto ids)
    {
        candidateService.deleteCandidates(ids);
    }


//    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
//    @ResponseStatus(HttpStatus.OK)
//    public void modifyCandidatePartial()
//    {
//        //just mentioning this possibility
//    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteCandidate(@PathVariable("id") Integer id)
//    {
//        //just mentioning this possibility
//    }

}
