package com.summercampquest.campquest.controllers;


import com.summercampquest.campquest.data.CampRepository;
import com.summercampquest.campquest.models.Camp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.Optional;


@RestController
@RequestMapping("/camp")
public class CampController {


    @Autowired
     private CampRepository campRepository;

//Camp details display
    @GetMapping("view/{id}")
    public ResponseEntity<Camp> displayViewCampDetail(@PathVariable int campId){
        Optional<Camp> camp= campRepository.findById(campId);
//                orElseThrow(()->new ResourceNotFoundException("Camp details not present "+campId));
        return new ResponseEntity<>(camp.get(), HttpStatus.OK);
    }

//    @GetMapping("view/{campId}")
//    public String displayViewCampdetail(Model model, @PathVariable int campId) {
//
//        Optional optCamp = campRespository.findById(campId);
//        if (optCamp.isPresent()) {
//            Camp camp = (Camp) optcamp.get();
//            model.addAttribute("camp", camp);
//            return "camp/view";
//        } else {
//            return "redirect:../";
//        }

//    }

    
}
