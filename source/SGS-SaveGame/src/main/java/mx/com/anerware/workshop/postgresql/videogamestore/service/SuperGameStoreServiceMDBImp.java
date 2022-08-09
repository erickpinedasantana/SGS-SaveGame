package mx.com.anerware.workshop.postgresql.videogamestore.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.anerware.workshop.postgresql.videogamestore.exception.VideoGameException;
import mx.com.anerware.workshop.postgresql.videogamestore.repository.VideoGamesRepositoryMDB;
import mx.com.anerware.workshop.postgresql.videogamestore.repository.entity.VideoGameUnitMDB;
import mx.com.anerware.workshop.postgresql.videogamestore.transformer.VideoGameSanitizing;
import mx.com.anerware.workshop.postgresql.videogamestore.transformer.VideoGameTransformer;
import mx.com.anerware.workshop.postgresql.videogamestore.transport.VideoGameUnitTR;

@Service
public class SuperGameStoreServiceMDBImp implements SuperGameStoreServiceMDB{

	@Autowired
	private VideoGamesRepositoryMDB videoGameRepositoryMDB;
	
	@Override
	public ObjectId saveGame(VideoGameUnitTR videoGame) throws VideoGameException {
		VideoGameUnitMDB videoGameDB = VideoGameTransformer.
		         map(VideoGameSanitizing.sanitizingInput(videoGame), 
		        		 VideoGameUnitMDB.class);
		//Save VideoGame
		ObjectId videoGameid = null;
		try {
			VideoGameUnitMDB videoGameResponse = 
					videoGameRepositoryMDB.saveOrUpdateVideoGame(videoGameDB);
			videoGameid = videoGameResponse.getId();
		}catch(Exception e) {
			throw new VideoGameException("Se ha generado un error: "
			                                  + e.getMessage(),e);
		}
		return videoGameid;
	}

}
