package dcmax.repositories;

import dcmax.models.Match;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
}
