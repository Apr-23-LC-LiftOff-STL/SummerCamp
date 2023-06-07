package com.summercampquest.campquest.controller;

import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.data.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.Comparator.comparing;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/camps")
public class CampController {
    @Autowired
    private CampRepository campRepository;

    private List<Camp> camps = new ArrayList<>();
    private List<Camp> filteredCamps;

    //get all camps REST API
    @GetMapping()
    public List<Camp> getAllCamps(){

        camps = (List<Camp>) campRepository.findAll();
        return camps;
    }

    //create camp REST API
    @PostMapping()
    public Camp createCamp(@RequestBody Camp camp){
        return campRepository.save(camp);
    }


    //get unique categories from list of camps
    @GetMapping("unique-categories")
    public Set<String> getUniqueCategories(){
        //get all camps
        getAllCamps();
        Set<String> uniqueCategories = new HashSet<>();
        for(Camp camp : camps){
            uniqueCategories.add(camp.getCategory());
        }
        return uniqueCategories;
    }

    public List<Camp> getCampsBySelectedCategories(String[] categories) {
        filteredCamps = new ArrayList<>();
        for (Camp camp : camps) {
            for (String item : categories) {
                if (camp.getCategory().equalsIgnoreCase(item)) {
                    filteredCamps.add(camp);
                }
            }
        }
        return filteredCamps;
    }

    @GetMapping("price")
    public List<Camp> getSelectedCampsSortedByPrice(@RequestParam String[] categories, @RequestParam String order) {
            if(order.equalsIgnoreCase("price: low to high")) {
                if (categories.length == 0)
                    filteredCamps = campRepository.findAllByOrderByPriceAsc();
                else{
                    getCampsBySelectedCategories(categories);
                    filteredCamps.sort(comparing(Camp::getPrice));
                }

            } else if(order.equalsIgnoreCase("price: high to low")) {
                if(categories.length == 0)
                    filteredCamps = campRepository.findAllByOrderByPriceDesc();
                else{
                    getCampsBySelectedCategories(categories);
                    filteredCamps.sort(comparing(Camp::getPrice).reversed());
                }
            }
            return filteredCamps;
    }
}

