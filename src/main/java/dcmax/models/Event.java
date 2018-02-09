package dcmax.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private boolean active = true;

    @Column(nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date lastUpdatedTime = new Date();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "eventOrganizers")
    private Set<User> organizers =  new HashSet<User>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EventType eventType;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EventLocation eventLocation;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event")
    private Set<Match> matches = new HashSet<Match>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "event_teams",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")})
    private Set<Team> teams = new HashSet<Team>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "event_participants",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> participants = new HashSet<User>();

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

    public Set<User> getOrganizers() { return organizers;}

    public void setOrganizers(Set<User> organizers) {
        this.organizers = organizers;
    }

    public boolean hasOrganizer(User organizer) {
        final User finalOrganizer = organizer;
        return getOrganizers().stream().anyMatch(r -> r.getId().equals(finalOrganizer.getId()));
    }

    public EventType getEventType() { return eventType;}

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public EventLocation getEventLocation() { return eventLocation;}

    public Set<Team> getTeams() { return teams;}

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public boolean hasTeam(Team team) {
        final Team finalTeam = team;
        return getTeams().stream().anyMatch(r -> r.getId().equals(finalTeam.getId()));
    }

    public Set<User> getParticipants() { return participants;}

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public boolean hasParticipant(User participant) {
        final User finalParticipant = participant;
        return getParticipants().stream().anyMatch(r -> r.getId().equals(finalParticipant.getId()));
    }

    public void setEventLocation(EventLocation eventLocation) {
        this.eventLocation = eventLocation;
    }

    public boolean getActive() { return active;}

    public void setActive(boolean active) { this.active = active;}

    public Date getCreateTime() { return createTime;}

    public void setCreateTime(Date createTime) { this.createTime = createTime;}

    public Date getLastUpdateTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdateTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

}
