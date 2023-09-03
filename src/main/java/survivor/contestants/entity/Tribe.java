//////////////this one is done

package survivor.contestants.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Tribe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tribeId;
	private String tribeName;
	
	@ManyToMany
	private Set<Contestant> contestants = new HashSet<>();
	

}
