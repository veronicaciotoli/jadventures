package com.generation.jadventures.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.generation.jadventures.dto.party.PartyDtoWFull;
import com.generation.jadventures.dto.party.PartyDtoWLogin;
import com.generation.jadventures.model.dtoservice.PartyConverter;
import com.generation.jadventures.model.entities.Party;
import com.generation.jadventures.model.repositories.PartyRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class PartyController 
{
    @Autowired
    PartyRepository repo;

    @Autowired
    PartyConverter conv;

    @PostMapping("parties/login")
    public ResponseEntity<?> loginParty(@RequestBody PartyDtoWLogin dto) 
    {
        Optional<Party> op= repo.findLogged(dto.getName(), dto.getAuthentication_seal());
        if(op.isPresent())
            return new ResponseEntity<PartyDtoWFull>(conv.partyDtoWFull(op.get()),HttpStatus.OK);
        else
        return new ResponseEntity<String>("Credenziali non valide",HttpStatus.UNAUTHORIZED);
        
    }
    

}
