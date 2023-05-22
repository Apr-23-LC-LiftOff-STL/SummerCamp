package com.summercampquest.campquest.models.data;

import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.Favorites;
import com.summercampquest.campquest.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorites,Integer> {
    Favorites findByUserAndCamp(User user, Camp camp);
    List<Favorites> findByUser(User user);

}
