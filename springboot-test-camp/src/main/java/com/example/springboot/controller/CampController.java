package com.example.springboot.controller;

import com.example.springboot.model.Camp;
import com.example.springboot.repository.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CampController {
    @Autowired
    private CampRepository campRepository;

    //get all camps REST API
    @GetMapping("camps")
    public List<Camp> getAllCamps(){
        return (List<Camp>) campRepository.findAll();
    }

    //create camp REST API
    @PostMapping("camps")
    public Camp createCamp(@RequestBody Camp camp){
        return campRepository.save(camp);
    }

}

