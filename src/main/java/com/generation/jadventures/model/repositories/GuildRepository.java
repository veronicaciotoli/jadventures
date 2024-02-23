package com.generation.jadventures.model.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generation.jadventures.model.entities.Guild;



public interface GuildRepository extends JpaRepository <Guild, Integer>
{
    @Query(value="SELECT * FROM guild WHERE name= :name AND authentication_seal= :authentication_seal",nativeQuery=true)
    Optional<Guild> findLogged (@Param ("name")String name, @Param ("authentication_seal")String authentication_seal);

    
}



