package com.generation.jadventures.dto.guild;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GuildDtoRput extends GuildDtoWFullNoQuests 
{
    private String authentication_seal;
}
