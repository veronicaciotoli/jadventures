package com.generation.jadventures.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.generation.jadventures.dto.quest.QuestDtoWFull;
import com.generation.jadventures.dto.quest.QuestDtoWFullWithPadron;
import com.generation.jadventures.model.dtoservice.QuestConverter;
import com.generation.jadventures.model.entities.Guild;
import com.generation.jadventures.model.entities.Quest;
import com.generation.jadventures.model.repositories.GuildRepository;
import com.generation.jadventures.model.repositories.QuestRepository;

@RestController
public class QuestController 
{
    @Autowired
    QuestConverter conv;

    @Autowired
    QuestRepository repo;

    @Autowired
    GuildRepository gRepo;

    @GetMapping("/quests")
    public List<QuestDtoWFull> getAllNoPadron()
    {
        return repo.findAll().stream().map(q -> conv.questDtoWFullNoPadron(q)).toList();
    }

    @GetMapping("/quests/{id}")
    public QuestDtoWFull getOneNoPadron(@PathVariable Integer id)
    {
        Optional<Quest> op = repo.findById(id);

        if(op.isEmpty())
            return null;

        return conv.questDtoWFullNoPadron(op.get());

    }

    @GetMapping("/quests/{id}/patron")
    public QuestDtoWFullWithPadron getOneWhitPadron(@PathVariable Integer id)
    {
        Optional<Quest> op = repo.findById(id);

        if(op.isEmpty())
            return null;

        

        return conv.questDtoWFullWithPadron(op.get());

    }

    @PostMapping("/quests/{patron_id}")
    public ResponseEntity<?> insert(@PathVariable Integer patron_id,@RequestBody Quest entity)
    {
        Optional<Guild> op = gRepo.findById(patron_id);
        if(op.isEmpty())
            return new ResponseEntity<String>("No Guilt with id"+patron_id,HttpStatus.NOT_FOUND);

        Guild e = op.get();
        entity.setPatron(e);
        return new ResponseEntity<Quest>(repo.save(entity),HttpStatus.OK);

    }

     @DeleteMapping("/quests/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) 
    {
        Optional<Quest> op = repo.findById(id);
        if(op.isPresent())
        {
            repo.deleteById(id);
            return new ResponseEntity<String>("",HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("No quest with id "+id,HttpStatus.NOT_FOUND);
    }

   
    

}
