package com.generation.jadventures.dto.guild;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GuildRDtoBase 
{
    private String name; 
    private String seal_img_url; 
    private int n_employees; 
    private String hq_address; 

}
