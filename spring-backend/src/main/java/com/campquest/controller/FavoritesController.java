package com.campquest.controller;

import com.campquest.models.Camp;
import com.campquest.models.Favorites;
import com.campquest.models.User;
import com.campquest.models.data.CampRepository;
import com.campquest.models.data.FavoritesRepository;
import com.campquest.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
        User user = userRepository.findByuserName(userName);
        Camp camp = campRepository.findById(campId).orElse(new Camp());
        Favorites favorite = new Favorites(user,camp);
        favoritesRepository.save(favorite);
    }

    //remove favorite REST API
    @PostMapping("remove")
    public void removeFromFavorites(@RequestParam Integer campId, @RequestParam("userName") String userName){
        User user = userRepository.findByuserName(userName);
        Camp camp = campRepository.findById(campId).orElse(new Camp());
        Favorites favorite = favoritesRepository.findByUserAndCamp(user, camp);
        if (favorite != null) {
            favoritesRepository.delete(favorite);
        }
    }


    //get all favorites of user
    @GetMapping("view")
    public List<Camp> viewFavorites(@RequestParam String userName){
        User user = userRepository.findByuserName(userName);
        List<Favorites> favoritesList = favoritesRepository.findByUser(user);
        List<Camp> campList = new ArrayList<>();
        for(Favorites favorites: favoritesList){
            campList.add(favorites.getCamp());
        }
        return campList;
    }
}