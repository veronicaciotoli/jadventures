package com.generation.jadventures.model.entities;

public enum TypeValid 
{
    DUGEON("DUGEON"),
    MONSTER_HOUNT("MONSTER_HOUNT"),
    VILLAGE_DEFENSE("VILLAGE_DEFENSE"),
    ERRAND("ERRAND"),
    BODYGUARD("BODYGUARD"),
    PATROL("PATROL");

    private final String type;
    
    public String typeVal()
    {
        return type;
    }

    TypeValid(String type)
    {
        this.type= type;
    }

}
