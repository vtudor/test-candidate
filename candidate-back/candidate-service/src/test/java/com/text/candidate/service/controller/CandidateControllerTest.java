package com.text.candidate.service.controller;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.service.CandidateService;
import com.test.candidate.service.CandidateServiceApp;
import com.test.candidate.service.controller.CandidateController;
import com.test.candidate.service.dto.CandidateDto;
import com.test.candidate.service.dto.IdDto;
import com.test.candidate.service.dto.IdsDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Tudor on 23-Sep-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CandidateServiceApp.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class CandidateControllerTest
{
    @Mock
    private CandidateService candidateService;

    @InjectMocks
    private CandidateController candidateController;

    private MockMvc mockMvc;

    private Candidate candidate;
    private CandidateDto dto;
    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(candidateController).build();

        candidate = new Candidate("1", true);
        dto = new CandidateDto(candidate);
    }

    @Test
    public void testGetCandidates() throws Exception
    {
        when(candidateService.getCandidates()).thenReturn(new ArrayList<CandidateDto>());
        mockMvc.perform(get("/candidate"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void testGetCandidatesWithId1() throws Exception
    {
        when(candidateService.getCandidate(1)).thenReturn(dto);
        mockMvc.perform(get("/candidate/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"1\",\"enabled\":true}"));
    }

    @Test
    public void testModifyCandidatesWithId1() throws Exception
    {
        mockMvc.perform(put("/candidate/1").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"tudor\",\"enabled\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateCandidatesWithId1() throws Exception
    {
        IdDto idDto = new IdDto();
        idDto.setId(4);

        when(candidateService.createCandidate(Mockito.any(CandidateDto.class))).thenReturn(idDto);
        mockMvc.perform(post("/candidate/").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"tudor\",\"enabled\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"id\":4}"));
    }

    @Test
    public void testDeleteCandidatesWithId1() throws Exception
    {
        List<Integer> ids = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        IdsDto idsDto = new IdsDto();
        idsDto.setIds(ids);

        mockMvc.perform(post("/candidate/delete").contentType(MediaType.APPLICATION_JSON)
                .content("{\"ids\": [1,2]}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
