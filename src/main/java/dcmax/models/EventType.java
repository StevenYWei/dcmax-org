package dcmax.models;
import javax.persistence.*;

@Entity
@Table(name = "eventtypes")
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventType() { return eventType;}
    public void setEventType(String eventType) { this.eventType = eventType; }
}
