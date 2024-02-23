package com.generation.jadventures.model.dtoservice;
import org.springframework.stereotype.Service;

import com.generation.jadventures.dto.guild.GuildDtoWFull;
import com.generation.jadventures.dto.guild.GuildDtoWFullNoQuests;
import com.generation.jadventures.dto.guild.GuildDtoWLogin;
import com.generation.jadventures.dto.guild.GuildRDtoBase;
import com.generation.jadventures.model.entities.Guild;

@Service
public class GuildConverter 
{


    public GuildDtoWFullNoQuests guildToDtoWFullNoQuests(Guild g)
    {
        return GuildDtoWFullNoQuests
                .builder()
                .id(g.getId())
                .name(g.getName())
                .seal_img_url(g.getSeal_img_url())
                .n_employees(g.getN_employees())
                .hq_address(g.getHq_address())
                .build();
    }
    
    public GuildDtoWFull guildToDtoWFull(Guild g)
    {
        return GuildDtoWFull
                .builder()
                .id(g.getId())
                .name(g.getName())
                .authentication_seal(g.getAuthentication_seal())
                .seal_img_url(g.getSeal_img_url())
                .n_employees(g.getN_employees())
                .hq_address(g.getHq_address())
                .posted_quests(g.getPosted_quests())
                .build();
    }

    public GuildDtoWLogin guildToDtoWLogin(Guild g)
    {
        return GuildDtoWLogin
                .builder()
                .name(g.getName())
                .authentication_seal(g.getAuthentication_seal())
                .build();
    }

    public Guild dtoLoginToGuild(GuildDtoWLogin dto)
    {
        return Guild
                .builder()
                .name(dto.getName())
                .authentication_seal(dto.getAuthentication_seal())
                .build();
    }


    public Guild dtoRBase(GuildRDtoBase dto)
    {
        return Guild
                .builder()
                .name(dto.getName())
                .seal_img_url(dto.getSeal_img_url())
                .n_employees(dto.getN_employees())
                .hq_address(dto.getHq_address())
                .build(); 
    }

    public GuildRDtoBase guildtoDtoBase (Guild e)
    {
        return GuildRDtoBase
                .builder()
                .name(e.getName())
                .seal_img_url(e.getSeal_img_url())
                .n_employees(e.getN_employees())
                .hq_address(e.getHq_address())
                .build(); 
    }

    

}
