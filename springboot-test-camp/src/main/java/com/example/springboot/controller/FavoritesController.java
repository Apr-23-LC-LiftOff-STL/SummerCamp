package com.example.springboot.controller;

import com.example.springboot.model.Camp;
import com.example.springboot.model.Favorites;
import com.example.springboot.model.User;
import com.example.springboot.repository.CampRepository;
import com.example.springboot.repository.FavoritesRepository;
import com.example.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/camps/favorites")
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
