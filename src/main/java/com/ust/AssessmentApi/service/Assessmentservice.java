package com.ust.AssessmentApi.service;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.ust.AssessmentApi.dto.Questionsdto;
import com.ust.AssessmentApi.dto.Setdto;
import com.ust.AssessmentApi.model.*;
import com.ust.AssessmentApi.repository.Optionsrepository;
import com.ust.AssessmentApi.repository.Questionsrepository;
import com.ust.AssessmentApi.repository.Setrepository;
import com.ust.AssessmentApi.utils.Apputils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Assessmentservice {

    @Autowired
    private Optionsrepository optionsrepository;

    @Autowired
    private Questionsrepository questionsRepository;

    @Autowired
    private Setrepository setrepository;


    public List<Set> getAllSets() {
        return setrepository.findAll();
    }

    public Set createSet(Setdto set1) {
        Set set = new Set();
        set.setSetname(set1.getSetname());
        set.setDomain(set1.getDomain());
        set.setCreatedby(Person.getName());
        set.setStatus(status.INITIATED);
        List<Questions> questionEntities = set1.getQuestionsList().stream()
                .map(questions -> {
                    Questions questionEntity = new Questions();
                    questionEntity.setSetname(set1.getSetname());
                    questionEntity.setQuestiontext(questions.getQuestiontext());
                    return questionEntity;
                })
                .collect(Collectors.toList());
        questionsRepository.saveAll(questionEntities);
        set.setQuestionList(questionEntities);
        setrepository.save(set);
        return set;

    }

    public Set getSet(String setname) {
        return setrepository.findBySetname(setname);
    }

    public Questions updateQuestion(String setname, Long questionId, Questionsdto qdto) {
        Questions questions = questionsRepository.findBySetname(setname);
        if (questions.getQuestionid()==questionId) {
            List<Options> optionsEntities = qdto.getOptions().stream()
                    .map(options -> {
                        Options optionsEntity = new Options();
                        optionsEntity.setQuestionid(qdto.getQuestionid());
                        optionsEntity.setValue(options.getValue());
                        optionsEntity.setSuggestions(options.getSuggestions());
                        return optionsEntity;
                    })
                    .collect(Collectors.toList());
            optionsrepository.saveAll(optionsEntities);
            questions.setOptions(optionsEntities);
            questionsRepository.save(questions);
        }
        return questions;
    }

    public void deleteQuestion(String setname, Long questionId) {
        Questions questions = questionsRepository.findBySetname(setname);
//        questionsRepository.deletebyId(questions.getQuestionid());
        questionsRepository.deleteById(questionId);
    }


//        question.setOptions(qdto.getOptions());
//        return questionsRepository.save(question);


//    public Set updateQuestion(String setname, Long questionId, Questionsdto qdto) {
//        Questions question = questionsRepository.findBySetname(setname);
//        question.setOptions(qdto.getOptions());
//        return questionsRepository.save(question);
//    }
}

