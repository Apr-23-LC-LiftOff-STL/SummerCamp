package com.campquest.controller;

import com.campquest.models.data.CampRepository;
import com.campquest.models.Camp;
import com.campquest.models.GradeGroup;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CampControllerTest {

    @MockBean
    private CampRepository campRepository;

    @Autowired
    private CampController campController;

    @Test
    public void testFindByGrade() {
        List<Camp> campResponse = new ArrayList<>();
        Camp camp = new Camp();
        camp.setCampLink("test");
        camp.setCategory("test");
        camp.setGradeGrp(GradeGroup.GKTO5);
        campResponse.add(camp);
        Mockito.when(campRepository.findByGradeGrp(ArgumentMatchers.any(GradeGroup.class))).thenReturn(campResponse);
        List<Camp> actuals = campController.findByGrade(GradeGroup.G6TO8);
        Assert.assertTrue(!actuals.isEmpty());
        Assert.assertEquals(campResponse, actuals);
    }
}