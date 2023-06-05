package com.summercampquest.campquest.controller;


import com.summercampquest.campquest.exception.CampNotFoundException;
import com.summercampquest.campquest.exception.UserNotFoundException;
import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.Favorites;
import com.summercampquest.campquest.models.User;
import com.summercampquest.campquest.models.data.CampRepository;
import com.summercampquest.campquest.models.data.FavoritesRepository;
import com.summercampquest.campquest.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/camps/favorites") //TODO: This should be just favorites
public class FavoritesController {

    @Autowired
    FavoritesRepository favoritesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CampRepository campRepository;



    //add favorite REST API
    @PostMapping("add")
    public void addToFavorites(@RequestParam Integer campId, @RequestParam("userName") String userName){
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UserNotFoundException("User Not found"));
        Optional<Camp> campOpt = campRepository.findById(campId);
        if(campOpt.isEmpty()){
            throw new CampNotFoundException("Camp Not found");
        }
        Favorites favorite = new Favorites(user,campOpt.get());
        favoritesRepository.save(favorite);
    }

    //remove favorite REST API
    @PostMapping("remove")
    public void removeFromFavorites(@RequestParam Integer campId, @RequestParam("userName") String userName){
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UserNotFoundException("User Not found"));
        Optional<Camp> campOpt = campRepository.findById(campId);
        if(campOpt.isEmpty()){
            throw new CampNotFoundException("Camp Not found");
        }
        Favorites favorite = favoritesRepository.findByUserAndCamp(user, campOpt.get());
        if (favorite != null) {
            favoritesRepository.delete(favorite);
        }
    }


    //get all favorites of user
    @GetMapping("view")
    public List<Camp> viewFavorites(@RequestParam String userName){
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UserNotFoundException("User Not found"));
        List<Favorites> favoritesList = favoritesRepository.findByUser(user);
        List<Camp> campList = new ArrayList<>();
        for(Favorites favorites: favoritesList){
            campList.add(favorites.getCamp());
        }
        return campList;
    }
}