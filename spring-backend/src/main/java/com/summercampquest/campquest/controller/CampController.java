package com.summercampquest.campquest.controller;

import com.summercampquest.campquest.exception.CampNotFoundException;
import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.Favorites;
import com.summercampquest.campquest.models.GradeGroup;
import com.summercampquest.campquest.models.data.CampRepository;
import com.summercampquest.campquest.models.data.FavoritesRepository;
import com.summercampquest.campquest.service.CampData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/camps")
public class CampController {
    @Autowired
    private CampRepository campRepository;
    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private CampData campData;

    @GetMapping
    public List<Camp> findByGrade(@RequestParam GradeGroup gradeGrp, @RequestParam(defaultValue = "price: low to high") String order) {

        if("price: low to high".equalsIgnoreCase(order)){
            return campRepository.findByGradeGrpOrderByPriceAsc(gradeGrp);
        } else{
            return campRepository.findByGradeGrpOrderByPriceDesc(gradeGrp);
        }
    }

    //create camp REST API
    @PostMapping
    public Camp createCamp(@RequestBody Camp camp){
        return campRepository.save(camp);
    }

    //Delete camp by ID REST API
    @DeleteMapping("/{campId}")
    public ResponseEntity<Object> deleteCamp(@PathVariable Integer campId) {
        Optional<Camp> campOpt = campRepository.findById(campId);
        if (campOpt.isPresent()) {
            List<Favorites> favorites = favoritesRepository.findByCamp(campOpt.get());
            if (!favorites.isEmpty()) {
                for (Favorites favorite : favorites) {
                    favoritesRepository.deleteById(favorite.getId());
                }
            }
            campRepository.deleteById(campId);
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            throw new CampNotFoundException("No Camp found");
        }


    }
    @PutMapping("/{campId}")
    public Camp updateCamp(@PathVariable Integer campId, @RequestBody Camp camp){
        boolean campOpt = campRepository.existsById(campId);
        if (campOpt) {
          campRepository.save(camp);
        } else {
            throw new CampNotFoundException("No Camp found");
        }
        return camp;
    }

/*    @GetMapping("/{campId}")
    public Camp getCampById(@PathVariable Integer campId){
        Optional<Camp> campOpt = campRepository.findById(campId);
        if(campOpt.isPresent()){
            return campOpt.get();
        }else{
            throw new CampNotFoundException("No camp found");
        }
    }*/
    //get unique categories from list of camps
    @GetMapping("unique-categories")
    public Set<String> getUniqueCategories(){
        //get all camps
        List<Camp> camps = campRepository.findAll();
        Set<String> uniqueCategories = new HashSet<>();
        for(Camp camp : camps){
            uniqueCategories.add(camp.getCategory());
        }
        return uniqueCategories;
    }

    @GetMapping("price")
    public List<Camp> getSelectedCampsSortedByPrice(@RequestParam String[] categories, @RequestParam(defaultValue = "price: low to high") String order,@RequestParam GradeGroup gradeGroup) {
        if("price: low to high".equalsIgnoreCase(order)){
            return campRepository.findByGradeGrpAndCategoryInOrderByPriceAsc(gradeGroup,categories);
        } else{
            return campRepository.findByGradeGrpAndCategoryInOrderByPriceDesc(gradeGroup,categories);
        }
    }

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
    @GetMapping("/search{name}")
    public ResponseEntity<List<Camp>> displayCamps(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "category", required = false) String category) {

        System.err.println(name + ":::" + category);
        category=null;
        List<Camp> camps = new ArrayList<>(0);
        if ((name == null || name.trim().isEmpty()) && (category == null || category.trim().isEmpty())) {
            camps.addAll(campData.displayCamps());
        } else  {
            camps.addAll(campData.searchCamps(name, category));
        }
        return new ResponseEntity<>(camps, HttpStatus.OK);
    }

}