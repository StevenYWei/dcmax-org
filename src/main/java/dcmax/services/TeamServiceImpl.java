package dcmax.services;

import dcmax.models.Event;
import dcmax.models.Team;
import dcmax.repositories.EventRepository;
import dcmax.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepo;

    @Override
    public List<Team> findAll() {
        return this.teamRepo.findAll();
    }

    @Override
    public Team findById(Long id){
        return this.teamRepo.findOne(id);
    }

    @Override
    public Team findByName(String teamName) {
        return this.teamRepo.findByUsername(teamName);
    }

    @Override
    public Team create(Team team){
        return this.teamRepo.save(team);
    }
}
