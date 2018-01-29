package dcmax.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String eventNameEng;

    @Column(length = 100)
    private String eventNameChn;

    @Column(nullable = false)
    private Date eventStartTime = new Date();

    @Column(nullable = false)
    private Date eventEndTime = new Date();

    @Column(nullable = false)
    private boolean isActive = true;

    @Column(nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date lastUpdatedTime = new Date();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User organizer;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EventType eventType;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EventLocation eventLocation;

    public Event() {}

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventNameEng() { return eventNameEng; }

    public void setEventNameEng(String eventNameEng) {
        this.eventNameEng = eventNameEng;
    }

    public String getEventNameChn() { return eventNameChn; }

    public void setEventNameChn(String eventNameChn) {
        this.eventNameChn = eventNameChn;
    }

    public Date getEventStartTime() { return eventStartTime;}

    public void setEventStartTime(Date eventStartTime) { this.eventStartTime = eventStartTime;}

    public Date getEventEndTime() { return eventEndTime;}

    public void setEventEndTime(Date eventEndTime) { this.eventEndTime = eventEndTime;}

    public User getOrganizer() { return organizer;}

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public EventType getEventType() { return eventType;}

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public EventLocation getEventLocation() { return eventLocation;}

    public void setEventLocation(EventLocation eventLocation) {
        this.eventLocation = eventLocation;
    }

    public boolean isActive() { return isActive;}

    public void setActive(boolean status) { this.isActive = status;}

    public Date getCreateTime() { return createTime;}

    public void setCreateTime(Date createTime) { this.createTime = createTime;}

    public Date getLastUpdateTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdateTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

}
