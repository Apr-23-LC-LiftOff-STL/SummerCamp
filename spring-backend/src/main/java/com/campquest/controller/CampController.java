package com.campquest.controller;

import com.campquest.models.Camp;
import com.campquest.models.GradeGroup;
import com.campquest.models.data.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/camps")
public class CampController {
    @Autowired
    private CampRepository campRepository;

    @Autowired
    private CampData campData;

    @GetMapping
    public List<Camp> findByGrade(@RequestParam GradeGroup gradeGrp) {
        System.out.println("CampController.findByGrade " + gradeGrp);
        List<Camp> camps = campRepository.findByGradeGrp(gradeGrp);
        return camps;
    }

    //create camp REST API
    @PostMapping
    public Camp createCamp(@RequestBody Camp camp){
        return campRepository.save(camp);
    }


    /**
     * Method to display all camps by Id
     */

    @GetMapping("/{id}")
    public ResponseEntity<Camp> displayViewId(@PathVariable("id") Integer campId) {
        Optional<Camp> camp = campData.displayCampById(campId);
        return new ResponseEntity<>(camp.get(), HttpStatus.OK);
    }


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

}