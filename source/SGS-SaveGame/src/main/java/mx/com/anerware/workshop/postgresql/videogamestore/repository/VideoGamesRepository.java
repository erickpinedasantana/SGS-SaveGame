package mx.com.anerware.workshop.postgresql.videogamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.anerware.workshop.postgresql.videogamestore.repository.entity.
VideoGameUnitBD;

public interface VideoGamesRepository extends 
                                      JpaRepository<VideoGameUnitBD, Integer>{
}
