package dcmax.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String teamNameEng;

    @Column(nullable = false, length = 30, unique = true)
    private String teamNameChn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "captain_id", nullable = false)
    private User captain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vice_captain_id")
    private User viceCaptain;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "team_members",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> members = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "teams")
    private Set<Event> events = new HashSet<>();

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teamHome")
//    private Set<Match> matchHome = new HashSet<Match>();
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teamGuest")
//    private Set<Match> matchGuest = new HashSet<Match>();

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date lastUpdatedTime = new Date();

    public Team() {}

    public Team(String teamNameEng, User captain) {
        this.teamNameEng = teamNameEng;
        this.captain = captain;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTeamNameEng() { return teamNameEng; }

    public void setTeamNameEng(String teamNameEng) {
        this.teamNameEng = teamNameEng;
    }

    public String getTeamNameChn() { return teamNameChn; }

    public void setTeamNameChn(String teamNameChn) {
        this.teamNameChn = teamNameChn;
    }

    public User getCaptain() { return captain;}

    public void setCaptain(User captain) {
        this.captain = captain;
    }

    public void transitionCaptainship(User newCaptain) {
        if(!Objects.equals(this.captain, newCaptain))
            this.captain = newCaptain;
    }

    public User getViceCaptain() { return viceCaptain;}

    public void setViceCaptain(User viceCaptain) {
        this.viceCaptain = viceCaptain;
    }

    public Set<User> getMembers() { return members;}

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public boolean hasMember(User member) {
        final User finalMember = member;
        return getMembers().stream().anyMatch(r -> r.getId().equals(finalMember.getId()));
    }

    public Set<Event> getEvents() { return events;}

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public boolean isInEvent(Event event) {
        final Event finalEvent = event;
        return getEvents().stream().anyMatch(r -> r.getId().equals(finalEvent.getId()));
    }

//    public Set<Match> getMatchHome() { return matchHome; }
//
//    public void setMatchmHome(Set<Match> matchHome) { this.matchHome = matchHome; }
//
//    public Set<Match> getMatchGuest() { return matchGuest; }
//
//    public void setMatchGuest(Set<Match> matchGuest) { this.matchGuest = matchGuest; }

    public boolean getActive() { return active;}

    public void setActive(boolean active) { this.active = active;}

    public Date getCreateTime() { return createTime;}

    public void setCreateTime(Date createTime) { this.createTime = createTime;}

    public Date getLastUpdateTime() { return lastUpdatedTime;}

    public void setLastUpdateTime(Date lastUpdatedTime) { this.lastUpdatedTime = lastUpdatedTime; }

    @Override
    public String toString() {
        return "Team{" +
                ", team name='" + teamNameEng + '\'' +
                ", captain='" + captain.getFirstNameEng() + ' ' + captain.getLastNameEng() + '\'' +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }

}


