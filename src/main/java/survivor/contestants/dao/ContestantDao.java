package survivor.contestants.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import survivor.contestants.entity.Contestant;

public interface ContestantDao extends JpaRepository<Contestant, Long> {

}
