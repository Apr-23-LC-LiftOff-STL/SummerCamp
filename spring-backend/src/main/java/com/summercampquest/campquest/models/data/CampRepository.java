package com.summercampquest.campquest.models.data;

import com.summercampquest.campquest.models.Camp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampRepository extends CrudRepository<Camp,Integer> {

    List<Camp> findAllByOrderByPriceAsc();
    List<Camp> findAllByOrderByPriceDesc();

}
