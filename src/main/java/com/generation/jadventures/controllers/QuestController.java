package com.generation.jadventures.controllers;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.jadventures.dto.quest.QuestDtoWFull;
import com.generation.jadventures.dto.quest.QuestDtoWFullWithPadron;
import com.generation.jadventures.model.dtoservice.QuestConverter;
import com.generation.jadventures.model.entities.Guild;
import com.generation.jadventures.model.entities.Party;
import com.generation.jadventures.model.entities.Quest;
import com.generation.jadventures.model.repositories.GuildRepository;
import com.generation.jadventures.model.repositories.PartyRepository;
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

    @Autowired
    PartyRepository pRepo;

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

        if (!isValidRank(entity)) 
        {
                return new ResponseEntity<String>("Invalid rank", HttpStatus.BAD_REQUEST);
        }
        if (!isValidType(entity)) 
        {
                return new ResponseEntity<String>("Invalid type", HttpStatus.BAD_REQUEST);
        }
        if (!isValidStatus(entity)) 
        {
                return new ResponseEntity<String>("Invalid status", HttpStatus.BAD_REQUEST);
        }
        if (!isValidDateCompleted(entity)) 
        {
                return new ResponseEntity<String>("Invalid date completed", HttpStatus.BAD_REQUEST);
        }
        

        return new ResponseEntity<Quest>(repo.save(entity),HttpStatus.OK);  

    }

    @PutMapping("/quests/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody Quest entity) 
    {
        Optional<Quest> op = repo.findById(id);
        if(!op.isPresent())
        {
            return new ResponseEntity<String>("No quest with id "+id,HttpStatus.NOT_FOUND);
        }
        
        if (!isValidRank(entity)) 
            {
                    return new ResponseEntity<String>("Invalid rank", HttpStatus.BAD_REQUEST);
            }
            if (!isValidType(entity)) 
            {
                    return new ResponseEntity<String>("Invalid type", HttpStatus.BAD_REQUEST);
            }
            if (!isValidStatus(entity)) 
            {
                    return new ResponseEntity<String>("Invalid status", HttpStatus.BAD_REQUEST);
            }
            if (!isValidDateCompleted(entity)) 
            {
                    return new ResponseEntity<String>("Invalid date completed", HttpStatus.BAD_REQUEST);
            }

        else
            entity.setId(id);
            return new ResponseEntity<Quest>(repo.save(entity),HttpStatus.OK);


    }

    @PutMapping("/quests/accepting/{id}/{party_id}")
    public ResponseEntity<?> accept(@PathVariable Integer id, @PathVariable Integer party_id){
        Optional<Quest> op = repo.findById(id);
        if(op.isPresent()){
            Optional<Party> opp = pRepo.findById(party_id);
            if(opp.isPresent()){
                Quest q = op.get();
                q.setStatus("PENDING");
                q.setPartyQuest(opp.get());
                return new ResponseEntity<Quest>(repo.save(q),HttpStatus.OK);
            }
            return new ResponseEntity<String>("No party with id "+party_id,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("No quest with id "+id,HttpStatus.NOT_FOUND);
        
    }

    @PutMapping("quests/succeding/{id}/{party_id}")
    public ResponseEntity<?> acceptSuccess(@PathVariable Integer id, @PathVariable Integer party_id){
        Optional<Quest> op = repo.findById(id);
        if(op.isPresent()){
            Optional<Party> opp = pRepo.findById(party_id);
            if(opp.isPresent()){
                Quest q = op.get();
                q.setStatus("SUCCESS");
                q.setPartyQuest(opp.get());
                return new ResponseEntity<Quest>(repo.save(q),HttpStatus.OK);
            }
            return new ResponseEntity<String>("No party with id "+party_id,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("No quest with id "+id,HttpStatus.NOT_FOUND);
        
    }

    @PutMapping("/quests/failing/{id}/{party_id}" )
    public ResponseEntity<?> acceptFailed(@PathVariable Integer id, @PathVariable Integer party_id){
        Optional<Quest> op = repo.findById(id);
        if(op.isPresent()){
            Optional<Party> opp = pRepo.findById(party_id);
            if(opp.isPresent()){
                Quest q = op.get();
                q.setStatus("FAILED");
                q.setPartyQuest(opp.get());
                return new ResponseEntity<Quest>(repo.save(q),HttpStatus.OK);
            }
            return new ResponseEntity<String>("No party with id "+party_id,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("No quest with id "+id,HttpStatus.NOT_FOUND);
        
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

    public boolean isValidRank(Quest q) 
    {   
        String rank = q.getRank();
        if 
        (   
            rank.equalsIgnoreCase("S") ||
            rank.equalsIgnoreCase("A") ||
            rank.equalsIgnoreCase("B") ||
            rank.equalsIgnoreCase("C") ||
            rank.equalsIgnoreCase("D")
        ) 
            return true;        
        else 
        {
            return false;
        }

    }

    public boolean isValidStatus(Quest q) 
    {
        String status =   q.getStatus();
        
        if 
        (   
            status.equalsIgnoreCase("AWAITING") ||
            status.equalsIgnoreCase("PENDING") ||
            status.equalsIgnoreCase("SUCCESS") ||
            status.equalsIgnoreCase("FAILED")    
        ) 
            return true;        
        else 
        {
            return false;
        }

    }

    public boolean isValidDateCompleted(Quest q) 
    {
        
        LocalDate date = q.getDate_completed();
        
        if(date!=null && (q.getStatus().equalsIgnoreCase("SUCCESS") || q.getStatus().equalsIgnoreCase("FAILED") )) 
            return true;      
        
        if(date==null && (q.getStatus().equalsIgnoreCase("PENDING") || q.getStatus().equalsIgnoreCase("AWAITING") ))
            return true;


        return false;
    }

    public boolean isValidType(Quest q) 
    {
        String type = q.getType();
        
        if 
        (   
        type.equalsIgnoreCase("dungeon")              ||
            type.equalsIgnoreCase("monster hunt")     ||
            type.equalsIgnoreCase("village defense")  ||
            type.equalsIgnoreCase("errand")           ||
            type.equalsIgnoreCase("bodyguard")        ||
            type.equalsIgnoreCase("patrol")
        ) 
            return true;        
        else 
        {
            return false;
        }

    }





   

   
    

}
