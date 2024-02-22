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

    /*@PostMapping("/guilds")
    public Guild insertGuild(@RequestBody GuildDtoWFull dto)
    {
        if(dto.getAuthentication_seal().length()<8 )
         
        return gRepo.save(gConv.guildToDtoWFull(dto));
    }*/


    /*public boolean isValid()
    {
        if()
    }*/
  
    @PutMapping("/guilds/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody Guild entity) 
    {
        Optional<Guild> op = gRepo.findById(id);
        if(op.isPresent())
        {
            entity.setId(id);
            return new ResponseEntity<Guild>(gRepo.save(entity),HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("No guild with id "+id,HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/guilds/{id}")
    public void deleteGuild(@PathVariable Integer id)
    {
        gRepo.deleteById(id);
    }

}
