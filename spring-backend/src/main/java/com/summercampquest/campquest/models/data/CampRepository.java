package com.summercampquest.campquest.models.data;

import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.GradeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CampRepository extends JpaRepository<Camp, Integer> {

    List<Camp> findByGradeGrpOrderByPriceAsc(GradeGroup gradeGroup);
    List<Camp> findByGradeGrpOrderByPriceDesc(GradeGroup gradeGroup);


    List<Camp> findByGradeGrpAndCategoryInOrderByPriceAsc(GradeGroup gradeGroup, String[] categories);

    List<Camp> findByGradeGrpAndCategoryInOrderByPriceDesc(GradeGroup gradeGroup, String[] categories);

    List<Camp> findByName(String name);

    List<Camp> findByDeadline(Date deadline);

    List<Camp> findByGradeGrp(GradeGroup gradeGroup);

    //String SEARCH_CAMPS = "select ca.* from camp ca where (:name is null OR lower(ca.name)=lower(:name))   AND (:category is null OR ca.CATEGORY=:category)";

    String SEARCH_CAMPS = "select ca.* from camps ca where (:name is null OR lower(ca.name)=lower(:name))   AND (:category is null OR ca.CATEGORY=:category)";


    String COUNT_SEARCH_CAMPS = "select count(*)  from (" + SEARCH_CAMPS + ")";


    @Query(nativeQuery = true, countQuery = COUNT_SEARCH_CAMPS, value = SEARCH_CAMPS)
    List<Camp> searchCampsByNameAndCategory(@Param("name") String name, @Param("category") String category);

}
