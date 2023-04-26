package com.summercampquest.campquest.controllers;

import com.summercampquest.campquest.models.Camp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;

import com.summercampquest.campquest.models.data.CampRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class HomeController {
    @Autowired
    CampRepository campRepository;

    @GetMapping("/camps")
    public ResponseEntity<List<Camp>> getAllTutorials(@RequestParam(required = false) String name) {

            List<Camp> camps = new ArrayList<>();

            if (name == null)
                campRepository.findAll().forEach(camps::add); /* Method references shorthand lambda expressions = campRepository.findAll().forEach(camp -> camps.add(camp)); */

            else
                campRepository.findByName(name);

            if (camps.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(camps, HttpStatus.OK);

    }

    @GetMapping("/camps/{id}")
    public ResponseEntity<Camp> getCampById(@PathVariable("id") Integer id) {
        Optional<Camp> campData = campRepository.findById(id);

        if (campData.isPresent()) {
            return new ResponseEntity<>(campData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/camps")
    public ResponseEntity<Camp> createCamps(@RequestBody Camp camp) {

            Camp _camp = campRepository
                    .save(new Camp(camp.getName(), camp.getDescription(), camp.getPrice(),camp.getDuration(), camp.getAge(), camp.getDeadline(), camp.getTotalSeats(), camp.getMode(), camp.getCategory(), camp.getCampLink()));
            return new ResponseEntity<>(_camp, HttpStatus.CREATED);

    }

    @PutMapping("/camps/{id}")
    public ResponseEntity<Camp> updateCamp(@PathVariable("id") Integer id, @RequestBody Camp camp) {
        Optional<Camp> campData = campRepository.findById(id);

        if (campData.isPresent()) {

            Camp _camp = campData.get();

            _camp.setName(camp.getName());
            _camp.setPrice(camp.getPrice());
            _camp.setDescription(camp.getDescription());
            _camp.setDuration(camp.getDuration());
            _camp.setAge(camp.getAge());
            _camp.setDeadline(camp.getDeadline());
            _camp.setTotalSeats(camp.getTotalSeats());
            _camp.setMode(camp.getMode());
            _camp.setCategory(camp.getCategory());
            _camp.setCampLink(camp.getCampLink());

            return new ResponseEntity<>(campRepository.save(_camp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/camps/{id}")
    public ResponseEntity<HttpStatus> deleteCamp(@PathVariable("id") Integer id) {

            campRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
