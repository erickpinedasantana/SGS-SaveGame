package mx.com.anerware.workshop.postgresql.videogamestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.anerware.workshop.postgresql.videogamestore.exception.
VideoGameException;
import mx.com.anerware.workshop.postgresql.videogamestore.repository.VideoGamesRepository;
import mx.com.anerware.workshop.postgresql.videogamestore.repository.entity.VideoGameUnitBD;
import mx.com.anerware.workshop.postgresql.videogamestore.transformer.VideoGameSanitizing;
import mx.com.anerware.workshop.postgresql.videogamestore.transformer.VideoGameTransformer;
import mx.com.anerware.workshop.postgresql.videogamestore.transport.
VideoGameIdxTR;
import mx.com.anerware.workshop.postgresql.videogamestore.transport.
VideoGameUnitTR;

@Service
@Transactional
public class SuperGameStoreServiceImp implements SuperGameStoreService{
	
	@Autowired
	private VideoGamesRepository videoGamesRepository;
	
	@Override
	 public VideoGameIdxTR saveGame(VideoGameUnitTR videoGame) 
			                                throws VideoGameException{
		//mapping to VideoGameUnitBD after sanitizing Strings
		VideoGameUnitBD videoGameDB = VideoGameTransformer.
		         map(VideoGameSanitizing.sanitizingInput(videoGame), 
		             VideoGameUnitBD.class);
		
		//Save VideoGame
		VideoGameIdxTR videoGameIdxTR = null;
		try {
		    videoGameDB = videoGamesRepository.save(videoGameDB);
		    videoGameIdxTR = new VideoGameIdxTR();
			videoGameIdxTR.setVideoGameIdx(videoGameDB.getVideoGameIdx());
		}catch(Exception e) {
			throw new VideoGameException("Se ha generado un error: "
		                                  + e.getMessage(),e);
		}
		
		return videoGameIdxTR;
	}

}