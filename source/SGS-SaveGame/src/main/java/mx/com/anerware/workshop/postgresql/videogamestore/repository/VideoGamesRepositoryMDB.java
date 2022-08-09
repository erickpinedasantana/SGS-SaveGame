package mx.com.anerware.workshop.postgresql.videogamestore.repository;

import mx.com.anerware.workshop.postgresql.videogamestore.
       repository.entity.VideoGameUnitMDB;

public interface VideoGamesRepositoryMDB {
	VideoGameUnitMDB saveOrUpdateVideoGame
	            (VideoGameUnitMDB videoGame);
}
