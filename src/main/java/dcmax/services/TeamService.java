package dcmax.services;


import dcmax.models.Team;

import java.util.List;

public interface TeamService {
    List<Team> findAll();

    Team findById(Long id);

    Team findByName(String teamName);

    Team create(Team event);
}
