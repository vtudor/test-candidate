package com.test.candidate.persistence.repository;

import com.test.candidate.persistence.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oleg on 09/08/15.
 */
//using JpaRepository because it has batch delete
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

}
