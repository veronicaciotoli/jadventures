package com.generation.jadventures.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.jadventures.dto.party.PartyDtoWFull;
import com.generation.jadventures.dto.party.PartyDtoWLogin;
import com.generation.jadventures.model.dtoservice.PartyConverter;
import com.generation.jadventures.model.dtoservice.QuestConverter;
import com.generation.jadventures.model.entities.Party;
import com.generation.jadventures.model.entities.Quest;
import com.generation.jadventures.model.repositories.PartyRepository;





@RestController
public class PartyController 
{
    @Autowired
    PartyRepository repo;

    @Autowired
    PartyConverter conv;

    @Autowired
    QuestConverter qConv;

    @Autowired
    PartyRepository pRepo;

    @PostMapping("parties/login")
    public ResponseEntity<?> loginParty(@RequestBody PartyDtoWLogin dto) 
    {
        Optional<Party> op= repo.findLogged(dto.getName(), dto.getAuthentication_seal());
        if(op.isPresent())
            return new ResponseEntity<PartyDtoWFull>(conv.dtoWFulltoPartyAug(op.get()),HttpStatus.OK);
        
        
        return new ResponseEntity<String>("Credenziali non valide",HttpStatus.UNAUTHORIZED);
        
    }

    @GetMapping("party/{id}")
    public PartyDtoWFull getParty(@PathVariable Integer id){
        Optional<Party> op = repo.findById(id);
        if(op.isEmpty())
        return null; 

    return conv.partyDtoWFull(pRepo.findById(id).get());
    }
    
    
    

}
