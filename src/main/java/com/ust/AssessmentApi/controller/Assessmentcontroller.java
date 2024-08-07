package com.ust.AssessmentApi.controller;


import com.ust.AssessmentApi.dto.Questionsdto;
import com.ust.AssessmentApi.dto.Setdto;
import com.ust.AssessmentApi.model.Questions;
import com.ust.AssessmentApi.model.Set;
import com.ust.AssessmentApi.service.Assessmentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping()
public class Assessmentcontroller {

    @Autowired
    private Assessmentservice assessmentService;

    @GetMapping("/assessments")
    public List<Set> getAllSets(){
        return assessmentService.getAllSets();
    }

    @PostMapping("/assessment")
    public Set createquestion(@RequestBody Setdto set1){
        return assessmentService.createSet(set1);

    }

    @GetMapping("/assessment/{setId}")
    public Set getSet(@PathVariable Long setId){
        return assessmentService.getSet(setId);
    }

    @PutMapping("/assessment/{setId}/{question_id}")
    public List<Questions >updateQuestion(@PathVariable Long setId, @PathVariable Long question_id, @RequestBody Questionsdto qdto){
        return assessmentService.updateQuestion(setId, question_id, qdto);
    }
    @DeleteMapping("/assessment/{setId}/{question_id}")
    public void deleteQuestion(@PathVariable Long setId, @PathVariable Long question_id){
        assessmentService.deleteQuestion(setId, question_id);
    }

}
