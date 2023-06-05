package com.summercampquest.campquest.controller;

import com.summercampquest.campquest.exception.CampNotFoundException;
import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.Favorites;
import com.summercampquest.campquest.models.GradeGroup;
import com.summercampquest.campquest.models.data.CampRepository;
import com.summercampquest.campquest.models.data.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/camps")
public class CampController {
    @Autowired
    private CampRepository campRepository;
    @Autowired
    private FavoritesRepository favoritesRepository;

    @GetMapping
    public List<Camp> findByGrade(@RequestParam GradeGroup gradeGrp) {

        List<Camp> camps = campRepository.findByGradeGrp(gradeGrp);
        return camps;
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
    public Camp updateCamp(@PathVariable Integer campId,@RequestBody Camp camp){
        boolean campOpt = campRepository.existsById(campId);
        if (campOpt) {
          campRepository.save(camp);
        } else {
            throw new CampNotFoundException("No Camp found");
        }
        return camp;
    }

    @GetMapping("/{campId}")
    public Camp getCampById(@PathVariable Integer campId){
        Optional<Camp> campOpt = campRepository.findById(campId);
        if(campOpt.isPresent()){
            return campOpt.get();
        }else{
            throw new CampNotFoundException("No camp found");
        }
    }
}