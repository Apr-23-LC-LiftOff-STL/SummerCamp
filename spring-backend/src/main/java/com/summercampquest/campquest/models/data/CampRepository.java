package com.summercampquest.campquest.models.data;

import com.summercampquest.campquest.models.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CampRepository extends JpaRepository<Camp, Integer> {

    List<Camp> findByName(String name);
    List<Camp> findByDate(Date date);
}
