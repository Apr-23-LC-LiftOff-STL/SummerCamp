package com.summercampquest.campquest.service;

import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.data.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CampData {


    private final CampRepository campRepository;


    @Autowired
    public CampData(CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    public Optional<Camp> displayCampById(Integer id) {
        return campRepository.findById(id);
    }


    public List<Camp> displayCamps() {
        return (List<Camp>) campRepository.findAll();
    }


}
