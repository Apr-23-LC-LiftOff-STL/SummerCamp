package com.summercampquest.campquest.controllers;

import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.service.CampData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/camps")
public class CampController {

    @Autowired
    private CampData campData;

    /**
     * Method to display all camps
     * OR
     * Search camps based on name, category
     * @param name
     * @param category
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Camp>> displayCamps(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "category", required = false) String category) {

        System.err.println(name + ":::" + category);

        List<Camp> camps = new ArrayList<>(0);
        if ((name == null || name.trim().isEmpty()) && (category == null || category.trim().isEmpty())) {
            camps.addAll(campData.displayCamps());
        } else  {
            camps.addAll(campData.searchCamps(name, category));
        }
        return new ResponseEntity<>(camps, HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<List<Camp>> displayCamps() {
//        List<Camp> camp = campData.displayCamps();
//        System.out.println(camp);
//
////                orElseThrow(()->new ResourceNotFoundException("Camp details not present "+campId));
//        return new ResponseEntity<>(camp, HttpStatus.OK);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Camp> displayViewId(@PathVariable("id") Integer campId) {
        Optional<Camp> camp = campData.displayCampById(campId);
        return new ResponseEntity<>(camp.get(), HttpStatus.OK);
    }

/*
    @GetMapping("view/{campId}")
    public String displayViewCampdetail(Model model, @PathVariable int campId) {

        Optional optCamp = campRespository.findById(campId);
        if (optCamp.isPresent()) {
            Camp camp = (Camp) optcamp.get();
            model.addAttribute("camp", camp);
            return "camp/view";
        } else {
            return "redirect:../";
        }
    }
*/

}
