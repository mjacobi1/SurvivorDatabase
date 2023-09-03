package survivor.contestants.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import survivor.contestants.entity.Contestant;
import survivor.contestants.entity.Season;

@Data
@NoArgsConstructor
public class ContestantData {
		private Long contestantId; 	
		private String firstName;
		private String lastName;
		private Set<Season> seasons = new HashSet<>();
		public ContestantData (Contestant contestant) {
			contestantId = contestant.getContestantId(); 	
			firstName = contestant.getFirstName();
			lastName = contestant.getLastName();
		}
	}