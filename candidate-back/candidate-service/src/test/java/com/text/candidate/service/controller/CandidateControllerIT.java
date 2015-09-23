package com.text.candidate.service.controller;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.repository.CandidateRepository;
import com.test.candidate.service.CandidateServiceApp;
import com.test.candidate.service.controller.CandidateController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Tudor on 23-Sep-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CandidateServiceApp.class)
@WebAppConfiguration
public class CandidateControllerIT
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateController candidateController;

    private MockMvc mockMvc;

    private boolean dbCreated = false;


    @Before
    public void setup()
    {
        if (!dbCreated)
        {
            dbCreated = true;

            List<Candidate> candidates = new ArrayList<Candidate>();
            candidates.add(new Candidate("joel", false));
            candidates.add(new Candidate("tudor", true));
            candidateRepository.deleteAll();
            candidateRepository.save(candidates);
        }

        mockMvc = webAppContextSetup(webApplicationContext).build();

    }


    @Test
    public void testGetCandidates() throws Exception
    {
        mockMvc.perform(get("/candidate"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"name\":\"joel\",\"enabled\":false},{\"name\":\"tudor\",\"enabled\":true}]"));
    }


    @Test
    public void testCreateCandidate() throws Exception
    {
        mockMvc.perform(post("/candidate/").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"tudor2\",\"enabled\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("{\"id\":")));      //"{\"id\":6}"

    }

    //TODO: add other integration tests
}
