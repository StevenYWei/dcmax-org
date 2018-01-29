package dcmax.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event_types")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String eventType;
    //training
    //tournament
    //match
    //party

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "eventType")
    private Set<Event> events = new HashSet<Event>();

    public EventType() {}

    public EventType(String eventType) {this.eventType = eventType;}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Set<Event> getEvents() { return events;}

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public String getEventType() { return eventType;}

    public void setEventType(String eventType) { this.eventType = eventType; }
}
