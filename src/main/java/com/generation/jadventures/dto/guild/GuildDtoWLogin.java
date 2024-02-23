package com.generation.jadventures.dto.guild;
import java.util.List;

import com.generation.jadventures.model.entities.Quest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GuildDtoWLogin 
{
    
    private String name; 
    private String authentication_seal;
}
