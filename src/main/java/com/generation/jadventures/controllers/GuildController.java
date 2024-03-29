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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.jadventures.dto.guild.GuildDtoRput;
import com.generation.jadventures.dto.guild.GuildDtoWFull;
import com.generation.jadventures.dto.guild.GuildDtoWLogin;
import com.generation.jadventures.model.dtoservice.GuildConverter;
import com.generation.jadventures.model.entities.Guild;
import com.generation.jadventures.model.repositories.GuildRepository;

@RestController
public class GuildController 
{
    @Autowired
    GuildRepository gRepo;
    @Autowired
    GuildConverter gConv; 

    @GetMapping("/guilds/full")
    public List<GuildDtoWFull> getAllGuildsFull()
    {
        return gRepo.findAll()
                .stream()
                .map(g-> gConv.guildToDtoWFull(g))
                .toList();
    }

    @GetMapping("/guilds/{id}")
    public GuildDtoWLogin getGuild(@PathVariable Integer id)
    {
        Optional<Guild> op = gRepo.findById(id);

        if(op.isEmpty())
            return null; 

        return gConv.guildToDtoWLogin(gRepo.findById(id).get());
    }



    @PostMapping("/guilds/login")
    public ResponseEntity <?> login(@RequestBody GuildDtoWLogin dto)
    {
        Optional<Guild> op= gRepo.findLogged(dto.getName(), dto.getAuthentication_seal());
        if(op.isPresent())
            return new ResponseEntity<GuildDtoWFull>(gConv.guildToDtoWFull(op.get()), HttpStatus.OK);

        else
        return new ResponseEntity<String>("Credenziali non valide", HttpStatus.UNAUTHORIZED); 
        
    }
    
    @PutMapping("/guilds/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody GuildDtoRput dto) 
    {
        Optional<Guild> op = gRepo.findById(id);
        if (!op.isPresent()) 
        {
            return new ResponseEntity<String>("No guild with id " + id, HttpStatus.NOT_FOUND);
        }
     
        if (!validAuthentication(dto.getAuthentication_seal())) {
            return new ResponseEntity<String>("Invalid authentication", HttpStatus.BAD_REQUEST);
        }

        Guild newVersion = gConv.dtoRPut(dto);

        gRepo.save(newVersion);

        return new ResponseEntity<String>("Guild modificato ",HttpStatus.OK);
    }
    
    public boolean validAuthentication(String authentication_seal )
    {
        
        char[] array = authentication_seal.toCharArray();


        if(array.length>=8)
        {
            for(char c: array)
            {
                if( 
                    Character.isUpperCase(c) &&
                    Character.isLowerCase(c) && 
                    Character.isDigit(c) && 
                    !Character.isLetterOrDigit(c)
                )    
                
                return true;                    
                         

            }
        }

        return true; 
        
        
    }

    
  


    @DeleteMapping("/guilds/{id}")
    public ResponseEntity<?> deleteGuild(@PathVariable Integer id)
    {
        Optional<Guild> op= gRepo.findById(id);
        if(op.isPresent())
        {
            gRepo.deleteById(id);
            return new ResponseEntity<String>("", HttpStatus.OK);
        }
        else 
            return new ResponseEntity<String>("No guild with id"+id, HttpStatus.NOT_FOUND);

    }

}
