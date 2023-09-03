///////////////// this one is done

package survivor.contestants.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Season {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seasonId;
	private String seasonLocation;
	private String seasonTagline;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Contestant> contestants = new HashSet<>();

}
