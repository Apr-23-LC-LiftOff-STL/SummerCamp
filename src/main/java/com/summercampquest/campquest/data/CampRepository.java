package com.summercampquest.campquest.data;

import com.summercampquest.campquest.models.Camp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository

public interface CampRepository extends CrudRepository<Camp, Integer> {

}
