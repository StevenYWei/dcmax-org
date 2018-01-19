package dcmax.services;

import dcmax.models.Event;

import java.util.List;

public interface EventService {
    List<Event> findAll();

    List<Event> findLatest5();

    Event findById(Long id);

    Event create(Event event);

    Event edit(Event event);
}
