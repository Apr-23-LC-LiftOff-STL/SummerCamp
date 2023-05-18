package com.campquest.models.data;

import com.campquest.models.Camp;
import com.campquest.models.GradeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CampRepository extends JpaRepository<Camp, Integer> {

    List<Camp> findByName(String name);

    List<Camp> findByDeadline(Date deadline);

    List<Camp> findByGradeGrp(GradeGroup gradeGroup);
}
