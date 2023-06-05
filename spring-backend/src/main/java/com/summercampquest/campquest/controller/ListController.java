package com.summercampquest.campquest.controller;


import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.data.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//must be the port number for the client - ng 4200
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ListController {
    @Autowired
    private CampRepository campRepository;

    @GetMapping("/camps")
    public ResponseEntity<List<Camp>> getAllCamps(@RequestParam(required = false) String name) {

            List<Camp> camps = new ArrayList<>();

            if (name == null)
                campRepository.findAll().forEach(camps::add); /* Method references shorthand lambda expressions = campRepository.findAll().forEach(camp -> camps.add(camp)); */

            else
                campRepository.findByName(name);

        if (camps.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(camps, HttpStatus.OK);
        }

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
                    .save(new Camp(camp.getName(), camp.getDescription(),camp.getLocation(), camp.getPrice(),camp.getDuration(), camp.getAge(), camp.getDeadline(), camp.getTotalSeats(), camp.getMode(), camp.getCategory(), camp.getCampLink(),camp.getGradeGrp()));
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