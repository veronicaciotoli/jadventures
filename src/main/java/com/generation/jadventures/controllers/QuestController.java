package com.generation.jadventures.controllers;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.generation.jadventures.model.dtoservice.QuestConverter;
import com.generation.jadventures.model.repositories.QuestRepository;

@RestController
public class QuestController 
{
    @Autowired
    QuestConverter conv;

    @Autowired
    QuestRepository repo;

    
    

}
