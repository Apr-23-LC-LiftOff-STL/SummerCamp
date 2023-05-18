package com.summercampquest.campquest.controllers;

import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.GradeGroup;
import com.summercampquest.campquest.models.data.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/camps")
public class CampController {
    @Autowired
    private CampRepository campRepository;

    @GetMapping
    public List<Camp> findByGrade(@RequestParam GradeGroup gradeGrp) {
        System.out.println("CampController.findByGrade " + gradeGrp);
        List<Camp> camps = campRepository.findByGradeGrp(gradeGrp);
        return camps;
    }
}
