//////////// i think this one is done

package survivor.contestants.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Contestant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contestantId;
	
	@EqualsAndHashCode.Exclude
	private String firstName;
	
	@EqualsAndHashCode.Exclude
	private String lastName;
	 
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "season_id", nullable = false)
	private Season season;
 
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "contestant_season",
			joinColumns = @JoinColumn(name = "contestant_id"),
			inverseJoinColumns = @JoinColumn(name = "season_id")
			)
	private Set<Tribe> tribes = new HashSet<>();
}
