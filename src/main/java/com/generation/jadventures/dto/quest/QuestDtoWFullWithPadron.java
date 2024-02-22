package com.generation.jadventures.dto.quest;

import com.generation.jadventures.dto.guild.GuildRDtoBase;
import com.generation.jadventures.model.entities.Guild;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class QuestDtoWFullWithPadron extends QuestDtoBase
{
    private Integer id;
    private GuildRDtoBase patron;

}
