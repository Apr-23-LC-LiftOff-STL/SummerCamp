package com.example.springboot.repository;

import com.example.springboot.model.Camp;
import com.example.springboot.model.Favorites;
import com.example.springboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorites,Integer> {
    Favorites findByUserAndCamp(User user, Camp camp);
    List<Favorites> findByUser(User user);

}
