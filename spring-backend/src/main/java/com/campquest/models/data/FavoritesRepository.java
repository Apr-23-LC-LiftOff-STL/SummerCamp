package com.campquest.models.data;

import com.campquest.models.User;
import com.campquest.models.Camp;
import com.campquest.models.Favorites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorites,Integer> {
    Favorites findByUserAndCamp(User user, Camp camp);
    List<Favorites> findByUser(User user);

}
