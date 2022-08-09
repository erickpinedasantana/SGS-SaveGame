package mx.com.anerware.workshop.postgresql.videogamestore.restcontroller;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.anerware.workshop.postgresql.videogamestore.restobj.
VideoGameGeneralData;
import mx.com.anerware.workshop.postgresql.videogamestore.restobj.
VideoGameUnit;
import mx.com.anerware.workshop.postgresql.videogamestore.service.SuperGameStoreService;
import mx.com.anerware.workshop.postgresql.videogamestore.service.SuperGameStoreServiceMDB;
import mx.com.anerware.workshop.postgresql.videogamestore.transformer.
VideoGameTransformer;
import mx.com.anerware.workshop.postgresql.videogamestore.transport.VideoGameIdxTR;
import mx.com.anerware.workshop.postgresql.videogamestore.transport.
VideoGameUnitTR;

@RestController
@RequestMapping("/")
@Tag(name = "Super Games Store", description="API Rest for Video Games")
public class SuperGamesStoreController {

	@Autowired
	private SuperGameStoreService superGameStoreService;
	
	@Autowired
	private SuperGameStoreServiceMDB superGameStoreServiceMDB;
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@Operation(summary = "Save a new Video Game",
	  description = "Save a new Video Game to Store",
	  tags = {"Save Video Games", "Insert Video Games"}
	)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", 
	    description = "Created element",
	    content = @Content(schema = @Schema(implementation = VideoGameGeneralData.class))),
	    @ApiResponse(responseCode = "400",
	    description = "Bad Request",
	    content = @Content(schema = @Schema(implementation = VideoGameGeneralData.class))),
	    @ApiResponse(responseCode = "500",
	    description = "Internal VideoGames Services error",
	    content = @Content(schema = @Schema(implementation = VideoGameGeneralData.class)))
	})
	@PostMapping(value = "/saveGame", 
	             produces = {"application/json", "application/xml"}
	)
	public ResponseEntity<VideoGameGeneralData> saveGame(
		@Parameter(description ="Video Game to add. Cannot be null or empty",
		required = true,
		schema = @Schema(implementation = VideoGameUnit.class))
		@Valid @RequestBody VideoGameUnit request){
		
		try {
			//Extract business object
			VideoGameUnitTR videoGameTR = VideoGameTransformer.
			         map(request, VideoGameUnitTR.class);
			
			//Send Object to save
			VideoGameIdxTR videoGameIdxTR = superGameStoreService
                    .saveGame(videoGameTR);

			//Construct response
			VideoGameGeneralData response = new VideoGameGeneralData();
			response.setCode("00");
			response.setDescription(new StringBuilder()
					            .append("Process completed with videogame ID: ")
					            .append(videoGameIdxTR.getVideoGameIdx())
					            .toString());
			response.setExtraInfo("1 Game Inserted");
			
			//Return response
			return new ResponseEntity<VideoGameGeneralData>(response,HttpStatus.CREATED);
		}catch(Exception e) {
			VideoGameGeneralData response = new VideoGameGeneralData();
			response.setCode("500");
			response.setDescription("Internal service error "
					              + "save video game");
			response.setExtraInfo("Exception: "+e.getMessage());
			return new ResponseEntity<VideoGameGeneralData>(response, 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Save a new Video Game",
			  description = "Save a new Video Game to Store",
			  tags = {"Save Video Games", "Insert Video Games"}
			)
			@ApiResponses(value = {
			    @ApiResponse(responseCode = "201", 
			    description = "Created element",
			    content = @Content(schema = @Schema(implementation = VideoGameGeneralData.class))),
			    @ApiResponse(responseCode = "400",
			    description = "Bad Request",
			    content = @Content(schema = @Schema(implementation = VideoGameGeneralData.class))),
			    @ApiResponse(responseCode = "500",
			    description = "Internal VideoGames Services error",
			    content = @Content(schema = @Schema(implementation = VideoGameGeneralData.class)))
			})
			@PostMapping(value = "/saveGameMDB", 
			             produces = {"application/json", "application/xml"}
			)
			public ResponseEntity<VideoGameGeneralData> saveGameMDB(
				@Parameter(description ="Video Game to add. Cannot be null or empty",
				required = true,
				schema = @Schema(implementation = VideoGameUnit.class))
				@Valid @RequestBody VideoGameUnit request){
				
				try {
					//Extract business object
					VideoGameUnitTR videoGameTR = VideoGameTransformer.
					         map(request, VideoGameUnitTR.class);
					
					//Send Object to save				
					ObjectId videoGameIdx = superGameStoreServiceMDB.saveGame(videoGameTR);

					//Construct response
					VideoGameGeneralData response = new VideoGameGeneralData();
					response.setCode("00");
					response.setDescription(new StringBuilder()
							            .append("Process completed with videogame ID: ")
							            .append(videoGameIdx)
							            .toString());
					response.setExtraInfo("1 Game Inserted");
					
					//Return response
					return new ResponseEntity<VideoGameGeneralData>(response,HttpStatus.CREATED);
				}catch(Exception e) {
					VideoGameGeneralData response = new VideoGameGeneralData();
					response.setCode("500");
					response.setDescription("Internal service error "
							              + "save video game");
					response.setExtraInfo("Exception: "+e.getMessage());
					return new ResponseEntity<VideoGameGeneralData>(response, 
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
}
