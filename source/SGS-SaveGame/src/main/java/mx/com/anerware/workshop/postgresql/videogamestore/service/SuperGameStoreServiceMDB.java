package mx.com.anerware.workshop.postgresql.videogamestore.service;

import org.bson.types.ObjectId;

import mx.com.anerware.workshop.postgresql.videogamestore.exception.VideoGameException;
import mx.com.anerware.workshop.postgresql.videogamestore.transport.VideoGameUnitTR;

public interface SuperGameStoreServiceMDB {
     public ObjectId saveGame(VideoGameUnitTR videoGame) 
    		 throws VideoGameException;
}
