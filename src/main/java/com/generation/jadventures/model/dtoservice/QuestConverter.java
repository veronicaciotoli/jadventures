package com.generation.jadventures.model.dtoservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.jadventures.dto.guild.GuildRDtoBase;
import com.generation.jadventures.dto.quest.QuestDtoRpost;
import com.generation.jadventures.dto.quest.QuestDtoWFull;
import com.generation.jadventures.dto.quest.QuestDtoWFullWithPadron;
import com.generation.jadventures.model.entities.Guild;
import com.generation.jadventures.model.entities.Quest;
import com.generation.jadventures.model.repositories.GuildRepository;


@Service
public class QuestConverter 
{
    @Autowired
    GuildRepository gRepo;

    @Autowired
    GuildConverter gConv;

    public Quest dtoRpostToQuest(QuestDtoRpost dto)
    {
        Guild padre = null;

        Integer patron_id = dto.getPatron_id();

        if(patron_id!=null)
        {
            Optional<Guild> op = gRepo.findById(patron_id);

            if(op.isPresent())
                padre = op.get();
        }
        return Quest
                .builder()
                .id(dto.getId())
                .date_created(dto.getDate_created())
                .status(dto.getStatus())
                .rank(dto.getRank())
                .reward(dto.getReward())
                .area(dto.getArea())
                .date_completed(dto.getDate_completed())
                .map_url(dto.getMap_url())
                .description(dto.getDescription())
                .type(dto.getType())
                .patron(padre)
                .build();
    }

    public QuestDtoWFull questDtoWFullNoPadron(Quest e)
    {
        return QuestDtoWFull
                .builder()
                .id(e.getId())
                .date_created(e.getDate_created())
                .status(e.getStatus())
                .rank(e.getRank())
                .reward(e.getReward())
                .area(e.getArea())
                .date_completed(e.getDate_completed())
                .map_url(e.getMap_url())
                .description(e.getDescription())
                .type(e.getType())
                .build();
                
    }   

    public QuestDtoWFullWithPadron questDtoWFullWithPadron(Quest e)
    {
        return QuestDtoWFullWithPadron
                .builder()
                .id(e.getId())
                .date_created(e.getDate_created())
                .status(e.getStatus())
                .rank(e.getRank())
                .reward(e.getReward())
                .area(e.getArea())
                .date_completed(e.getDate_completed())
                .map_url(e.getMap_url())
                .description(e.getDescription())
                .type(e.getType())
                .patron(gConv.guildtoDtoBase(e.getPatron()))
                .build();
            
                
    } 
}
