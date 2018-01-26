package dcmax.repositories;

import dcmax.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query(value = "select t from Team t where t.teamNameEng like :teamName")
    Team findByUsername(@Param("teamName") String teamName);
}
