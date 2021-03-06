package dcmax.services;

import dcmax.controllers.ForbiddenException;
import dcmax.models.Event;
import dcmax.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private UserService userService;

    @Override
    public List<Event> findAll() {
        return this.eventRepo.findAll();
    }

    @Override
    public List<Event> findLatest5() {
        return this.eventRepo.findLatest5Events(new PageRequest(0, 5));
    }

    @Override
    public Event findById(Long id) {
        return this.eventRepo.findOne(id);
    }

    @Override
    public Event create(Event event) {

        if (!userService.isEventOrganizer() && !userService.isAdmin()) {
            throw new ForbiddenException();
        }
        return this.eventRepo.save(event);
    }

    @Override
    public Event edit(Event event) {
        return this.eventRepo.save(event);
    }

}

