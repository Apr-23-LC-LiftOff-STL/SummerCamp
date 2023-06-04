package com.summercampquest.campquest.models.data;

import com.summercampquest.campquest.models.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampRepository extends JpaRepository<Camp, Integer> {

    String SEARCH_CAMPS = "select ca.* from camp ca where (:name is null OR lower(ca.name)=lower(:name))   AND (:category is null OR ca.CATEGORY=:category)";
    String COUNT_SEARCH_CAMPS = "select count(*)  from (" + SEARCH_CAMPS + ")";




    @Query(nativeQuery = true, countQuery = COUNT_SEARCH_CAMPS, value = SEARCH_CAMPS)
    List<Camp> searchCampsByNameAndCategory(@Param("name") String name, @Param("category") String category);




}
