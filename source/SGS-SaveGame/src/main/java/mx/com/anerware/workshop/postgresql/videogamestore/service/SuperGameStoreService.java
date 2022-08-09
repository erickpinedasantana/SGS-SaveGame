package mx.com.anerware.workshop.postgresql.videogamestore.service;

import mx.com.anerware.workshop.postgresql.videogamestore.exception.VideoGameException;
import mx.com.anerware.workshop.postgresql.videogamestore.transport.VideoGameIdxTR;
import mx.com.anerware.workshop.postgresql.videogamestore.transport.VideoGameUnitTR;

public interface SuperGameStoreService {
     public VideoGameIdxTR saveGame(VideoGameUnitTR videoGame) 
    		 throws VideoGameException;
}
