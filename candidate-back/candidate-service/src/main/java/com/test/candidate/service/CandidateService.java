package com.test.candidate.service;

import com.test.candidate.service.dto.CandidateDto;
import com.test.candidate.service.dto.IdDto;
import com.test.candidate.service.dto.IdsDto;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Tudor on 22-Sep-15.
 */
public interface CandidateService
{
 CandidateDto getCandidate(@NotNull Integer id);

 List<CandidateDto> getCandidates();

 IdDto createCandidate(CandidateDto dto);

 void modifyCandidate(@NotNull Integer id, CandidateDto dto);

 @Transactional
 //should use 1 transaction for delete 1 by 1 ( JpA CRUD methods on your repository are transactional by default)
 void deleteCandidates(IdsDto ids);
}
