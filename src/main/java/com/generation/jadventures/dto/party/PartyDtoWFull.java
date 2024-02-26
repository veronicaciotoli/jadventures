package com.generation.jadventures.dto.party;

import java.util.List;

import com.generation.jadventures.model.entities.Adventurer;
import com.generation.jadventures.model.entities.Party;
import com.generation.jadventures.model.entities.Quest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PartyDtoWFull 
{
    private Integer id;
    private String name,authentication_seal,motto;
    private Adventurer party_leader;
    private List<Quest> quests;
    private List<Adventurer> adventurers;
    private String evaluateRank;
}
