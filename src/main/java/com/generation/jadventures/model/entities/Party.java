package com.generation.jadventures.model.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Party 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name,authentication_seal,motto;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adventurer_id")//1-1
    private Adventurer party_leader;

    @OneToMany(mappedBy = "party",fetch = FetchType.EAGER)
    private List<Adventurer> adventurers;//1-N

    @OneToMany(mappedBy = "partyQuest",fetch = FetchType.EAGER)
    private List<Quest> quests;//1-N

    // Il rank del party viene calcolato come media tra il rank dell'avv più
    // debole e la media dei rank degli altri 3
    public String evaluateRank()
    {
        int sum = 0;
        if (adventurers == null || adventurers.size() > 4) 
        {
            return "NON DISPONIBILE";
        }
        else
        {
            for(Adventurer a: adventurers)
            {
                String rank=a.getRank();
                int numericRank= intforChar(rank);
                System.out.println(numericRank);
                sum+=numericRank; 
            }
        }

        return "Il rank medio del party è "+sum/adventurers.size();
    }

    public int intforChar(String rank)
    {
        String ranks = "";


        switch (ranks) 
        {
            case "S":
                return 5;    
                
            case "A":

                return 4;
            case "B":

                return 3;
            case "C":

                return 2;
            case "D":

                return 1;
    
            default:
                return -1;
        }
    }
}
