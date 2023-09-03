package survivor.contestants.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import survivor.contestants.entity.Contestant;
import survivor.contestants.entity.Season;
import survivor.contestants.entity.Tribe;

@Data
@NoArgsConstructor
public class SeasonData {
	private Long seasonId;
	private String seasonLocation;
	private String seasonTagline;
	private Set<ContestantData> contestants = new HashSet<>();
	
	public SeasonData (Season season) {
		this.seasonId = season.getSeasonId();
		this.seasonLocation = season.getSeasonLocation();
		this.seasonTagline = season.getSeasonTagline();
		
		for (Contestant contestant : season.getContestants()) {
			this.contestants.add(new ContestantData(contestant));
		}
	}
	
	public SeasonData (Long seasonId, String seasonLocation, String seasonTagline) {
		this.seasonId = seasonId;
		this.seasonLocation = seasonLocation;
		this.seasonTagline = seasonTagline;
	}
	
	public Season toSeason() {
		Season season = new Season();
		
		season.setSeasonId(seasonId);
		season.setSeasonLocation(seasonLocation);
		season.setSeasonTagline(seasonTagline);
		
		for (ContestantData contestantData : contestants) {
			season.getContestants().add(contestantData.toContestant());
		}
		
		return season;
	}
	
	@Data
	@NoArgsConstructor
	public class ContestantData {
		private Long contestantId;
		private String firstName;
		private String lastName;
		private Set<TribeData> tribes = new HashSet<>();
		
		public ContestantData (Contestant contestant) {
			this.contestantId = contestant.getContestantId();
			this.firstName = contestant.getFirstName();
			this.lastName = contestant.getLastName();
			
			for (Tribe tribe : contestant.getTribes()) {
				this.tribes.add(new TribeData(tribe));
			}
		}
		
		public Contestant toContestant() {
			Contestant contestant = new Contestant();
			
			contestant.setContestantId(contestantId);
			contestant.setFirstName(firstName);
			contestant.setLastName(lastName);
			
			for (TribeData tribeData : tribes) {
				
				contestant.getTribes().add(tribeData.toTribe());
			}
			
			return contestant;
		}
	}
	
	@Data
	@NoArgsConstructor
	public class TribeData {
		private Long tribeId;
		private String tribeName;
		
		public TribeData (Tribe tribe) {
			this.tribeId = tribe.getTribeId();
			this.tribeName = tribe.getTribeName();
		}
		
		public Tribe toTribe() {
			Tribe tribe = new Tribe();
			
			tribe.setTribeId(tribeId);
			tribe.setTribeName(tribeName);
			
			return tribe;
		}
	}
}

