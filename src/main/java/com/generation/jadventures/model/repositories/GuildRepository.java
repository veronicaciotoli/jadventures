package com.generation.jadventures.model.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.jadventures.model.entities.Guild;

public interface GuildRepository extends JpaRepository <Guild, Integer>
{
    Optional<Guild> findByNameAndAuthentication_seal(String name,String authentication_seal);
}



