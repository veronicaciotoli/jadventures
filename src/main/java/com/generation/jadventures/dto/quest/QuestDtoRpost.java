package com.generation.jadventures.dto.quest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class QuestDtoRpost extends QuestDtoBase 
{
    private Integer id;
    private Integer patron_id;

    
}
