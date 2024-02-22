package com.generation.jadventures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.jadventures.model.dtoservice.GuildConverter;
import com.generation.jadventures.model.repositories.GuildRepository;

@RestController
public class GuildController 
{
    @Autowired
    GuildRepository gRepo;
    @Autowired
    GuildConverter gConv; 


}
