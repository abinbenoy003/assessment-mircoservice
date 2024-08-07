package com.ust.AssessmentApi.repository;

import com.ust.AssessmentApi.model.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Optionsrepository extends JpaRepository<Options,Long> {

}
