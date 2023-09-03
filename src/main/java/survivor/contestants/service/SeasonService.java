
package survivor.contestants.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import survivor.contestants.controller.model.ContestantData;
import survivor.contestants.controller.model.SeasonData;
import survivor.contestants.dao.ContestantDao;
import survivor.contestants.dao.SeasonDao;
import survivor.contestants.entity.Contestant;
import survivor.contestants.entity.Season;

@Service
public class SeasonService {
	
	@Autowired
	private SeasonDao seasonDao;
	@Autowired
	private ContestantDao contestantDao;

	
	/////////////////SEASONS
	
	@Transactional(readOnly = false)
	public SeasonData saveSeason(SeasonData seasonData) {
		Season season = seasonData.toSeason();
		Season dbSeason = seasonDao.save(season);
		
		return new SeasonData(dbSeason);
	}

	@Transactional(readOnly = true)
	public SeasonData retrieveSeasonById(Long seasonId) {
		Season season = findSeasonById(seasonId);
		return new SeasonData(season);
	}

	private Season findSeasonById(Long seasonId) {
		return seasonDao.findById(seasonId).orElseThrow(() -> new NoSuchElementException("Season with that ID not found"));
	}

	@Transactional(readOnly = true)
	public List<SeasonData> retrieveAllSeasons() {	
		// @formatter:off
		return seasonDao.findAll().stream().map(seas -> new SeasonData(seas))
.toList();
		// @formatter:on
		}

	@Transactional(readOnly = false)
	public void deleteSeason(Long seasonId) {
		Season season = findSeasonById(seasonId);
		seasonDao.delete(season);
	}
	
	////////////////CONTESTANTS
	
	@Transactional(readOnly = false)
	public ContestantData saveContestant(Long seasonId, ContestantData contestantData) {
		Season season = findSeasonById(seasonId);
		Long contestantId = contestantData.getContestantId();
		Contestant contestant = findOrCreateContestant(seasonId, contestantId);
		copyContestantFields(contestantData, contestant);
		contestant.setSeason(season);
		season.getContestants().add(contestant);
		return new ContestantData(contestantDao.save(contestant));
	}

	private void copyContestantFields(ContestantData contestantData, Contestant contestant) {
		contestant.setContestantId(contestantData.getContestantId());
		contestant.setFirstName(contestantData.getFirstName());
		contestant.setLastName(contestantData.getLastName());
	}

	private Contestant findOrCreateContestant(Long seasonId, Long contestantId) {
		if(Objects.isNull(contestantId)) {
			return new Contestant();
		}
		return findContestantById(contestantId, seasonId);
	}
	

	private Contestant findContestantById(Long contestantId, Long seasonId) {
	Contestant contestant = contestantDao.findById(contestantId).orElseThrow(() -> new NoSuchElementException("Contestant with that ID not found."));
	
	if(contestant.getSeason().getSeasonId() != seasonId) {
		throw new IllegalArgumentException("The contestant with that ID did not play on the season with that Id.");
	}
	return contestant;
}

}
