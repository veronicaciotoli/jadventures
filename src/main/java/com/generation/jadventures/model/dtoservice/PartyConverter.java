package com.generation.jadventures.model.dtoservice;

import org.springframework.stereotype.Service;

import com.generation.jadventures.dto.party.PartyDtoWFull;
import com.generation.jadventures.dto.party.PartyDtoWLogin;
import com.generation.jadventures.model.entities.Party;

@Service
public class PartyConverter 
{
    public PartyDtoWFull partyDtoWFull (Party p)
    {
        return PartyDtoWFull
                .builder()
                .id(p.getId())
                .name(p.getName())
                .authentication_seal(p.getAuthentication_seal())
                .motto(p.getMotto())
                .party_leader(p.getParty_leader())
                .quests(p.getQuests())
                .adventurers(p.getAdventurers())
                .build();
    }

    public Party dtoWFulltoParty (PartyDtoWFull dto)
    {
        return  Party
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .authentication_seal(dto.getAuthentication_seal())
                .motto(dto.getMotto())
                .party_leader(dto.getParty_leader())
                .quests(dto.getQuests())
                .adventurers(dto.getAdventurers())
                .build();
    }

    public Party dtoWFulltoParty (PartyDtoWLogin dto)
    {
        return  Party
                .builder()
                .name(dto.getName())
                .authentication_seal(dto.getAuthentication_seal())
                .build();
    }

}
