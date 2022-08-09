package mx.com.anerware.workshop.postgresql.videogamestore.repository;

import javax.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import mx.com.anerware.workshop.postgresql.
       videogamestore.repository.entity.VideoGameUnitMDB;

@Repository
public class VideoGamesRepositoryMDBImp implements 
    VideoGamesRepositoryMDB{

	//Mongo db client
	private final MongoClient client;
	private MongoCollection<VideoGameUnitMDB> videoGameCollection;
	
	public VideoGamesRepositoryMDBImp(MongoClient client) {
		this.client=client;
	}
	
	@PostConstruct
    void init() {
		videoGameCollection = client.getDatabase("VideoGamesDB").
        		getCollection("videoGames", VideoGameUnitMDB.class);
    }
	
	@Override
	public VideoGameUnitMDB saveOrUpdateVideoGame
	   (VideoGameUnitMDB videoGame) {
		   videoGame.setId(new ObjectId());
		   videoGameCollection.insertOne(videoGame);
		   return videoGame;
	}
}
