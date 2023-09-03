////////////////THIS ONE IS DONE

package survivor.contestants.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import survivor.contestants.controller.model.ContestantData;
import survivor.contestants.controller.model.SeasonData;
import survivor.contestants.service.SeasonService;

@RestController
@RequestMapping("/survivor")
@Slf4j
public class SeasonController {
	
	@Autowired
	private SeasonService seasonService;
	
	////////////////SEASONS
	
	@PostMapping("/seasons")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SeasonData createSeason (@RequestBody SeasonData seasonData) {
		log.info("Creating season {}", seasonData);
		return seasonService.saveSeason(seasonData);
	}
	
	@PutMapping("/seasons/{seasonId}")
	public SeasonData update(@PathVariable Long seasonId, @RequestBody SeasonData seasonData) {
		seasonData.setSeasonId(seasonId);
		log.info("Updating season {}", seasonData);
		return seasonService.saveSeason(seasonData);
	}
	
	@GetMapping("/seasons/{seasonId}")
	public SeasonData retrieveSeason(@PathVariable Long seasonId) {
		log.info("Retrieving season");
		return seasonService.retrieveSeasonById(seasonId);
	}
	
	@GetMapping("/seasons")
	public List<SeasonData> retrieveAllSeasons() {
		log.info("Retrieving all seasons");
		return seasonService.retrieveAllSeasons();
	}
	
	@DeleteMapping("/seasons/{seasonId}")
	public Map<String, String> deleteSeason(@PathVariable Long seasonId) {
		log.info("Seasons with ID=" + seasonId + " has been deleted.");
		seasonService.deleteSeason(seasonId);
		return Map.of("message", "Season with Id=" + seasonId + " was deleted successfully.");
	}
	
	//////////CONTESTANTS
	
	@PostMapping("/{seasonId}/contestant")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ContestantData addContestant(@PathVariable Long seasonId, @RequestBody ContestantData contestantData) {
		log.info("You have added a contestant to the season.");
		return seasonService.saveContestant(seasonId, contestantData);
	}
	
}



