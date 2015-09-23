package com.test.candidate.service;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.repository.CandidateRepository;
import com.test.candidate.service.dto.CandidateDto;
import com.test.candidate.service.dto.IdDto;
import com.test.candidate.service.dto.IdsDto;
import com.test.candidate.service.exception.EntitiesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by oleg on 12/08/15.
 */
@Service
public class CandidateServiceImpl implements CandidateService
{
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    NotificationService notificationService;

    @Override
    public CandidateDto getCandidate(@NotNull Integer id)
    {
        Candidate candidate = candidateRepository.findOne(id);
        if (candidate == null)
            throw new EntityNotFoundException("Candidate with id " + id + " not found");

        CandidateDto dto = new CandidateDto(candidate);

        return dto;
    }

    @Override
    public List<CandidateDto> getCandidates()
    {
        List<Candidate> candidates = candidateRepository.findAll();
        List<CandidateDto> dtos = new ArrayList<CandidateDto>();
        for (Candidate c : candidates)
            dtos.add(new CandidateDto(c));

        return dtos;
    }

    @Override
    public IdDto createCandidate(CandidateDto dto)
    {
        Candidate candidate = new Candidate(dto.getName(), dto.getEnabled());
        candidate = candidateRepository.save(candidate);

        notificationService.addCandidate(dto);

        IdDto idDto = new IdDto();
        idDto.setId(candidate.getId());

        return idDto;
    }


    @Override
    public void modifyCandidate(@NotNull Integer id, CandidateDto dto)
    {
        Candidate candidate = candidateRepository.findOne(id);
        candidate.setName(dto.getName());
        candidate.setEnabled(dto.getEnabled());
        candidateRepository.save(candidate);
    }


    @Override
    @Transactional
    //should use 1 transaction for delete 1 by 1 ( JpA CRUD methods on your repository are transactional by default)
    public void deleteCandidates(IdsDto ids)
    {
        Set<Integer> uniqueIds = new HashSet<Integer>(ids.getIds());
        List<Integer> nonExistingEntities = null;
        for (Integer id : uniqueIds)
        {
            if (candidateRepository.exists(id))
            {
                candidateRepository.delete(id);
            }
            else
            {
                if (nonExistingEntities == null)
                    nonExistingEntities = new ArrayList<Integer>();
                nonExistingEntities.add(id);

            }
        }

        if (nonExistingEntities != null)
            throw new EntitiesNotFoundException("Could not delete entities with ids " + Arrays.toString(nonExistingEntities.toArray()));
    }
}
