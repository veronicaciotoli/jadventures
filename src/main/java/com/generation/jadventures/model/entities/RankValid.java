package com.generation.jadventures.model.entities;

public enum RankValid {

    S("S"),
    A("A"),
    B("B"),
    C("C"),
    D("D");
    
    private final String str;

    RankValid(String str)
    {
        this.str = str;
    }

    public String getStr()
    {
        return str;
    }
}
