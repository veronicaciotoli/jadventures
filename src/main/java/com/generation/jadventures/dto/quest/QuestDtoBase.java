package com.generation.jadventures.dto.quest;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class QuestDtoBase 
{
    private LocalDate date_created, date_completed;
    private String status, rank, map_url, description, type;
    private int reward;

}
