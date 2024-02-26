package com.generation.jadventures.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generation.jadventures.model.entities.Party;

public interface PartyRepository extends JpaRepository<Party,Integer>
{
    @Query(value="SELECT * FROM party WHERE name= :name AND authentication_seal= :authentication_seal",nativeQuery=true)
    Optional<Party> findLogged (@Param ("name")String name, @Param ("authentication_seal")String authentication_seal);
}
