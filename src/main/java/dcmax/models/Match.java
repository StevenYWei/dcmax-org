package dcmax.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_home_id", nullable = false)
    private Team teamHome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_guest_id", nullable = false)
    private Team teamGuest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referee_id")
    private User referee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assist_referee1_id")
    private User assistReferee1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assist_referee2_id")
    private User assistReferee2;

    @Column
    private Boolean active = true;

    @Column(nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date lastUpdatedTime = new Date();

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Event getEvent() { return event;}

    public void setEvent(Event event) {
        this.event = event;
    }

    public Team getTeamHome(){return teamHome; }

    public Team setTeamHome(Team teamHome){return this.teamHome = teamHome; }

    public Team getTeamGuest(){return teamGuest; }

    public Team setTeamGuest(Team teamGuest){return this.teamGuest = teamGuest; }

    public User getReferee() {
        return referee;
    }

    public void setReferee(User referee) {
        this.referee = referee;
    }

    public User getAssistReferee1() {
        return assistReferee1;
    }

    public void setAssistReferee1(User assistReferee1) {
        this.assistReferee1 = assistReferee1;
    }

    public User getAssistReferee2() {
        return assistReferee2;
    }

    public void setAssistReferee2(User assistReferee2) {
        this.assistReferee2 = assistReferee2;
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

    public Match() {}

    public Match(Event event, Team teamHome, Team teamGuest) {
        this.event = event;
        this.teamHome = teamHome;
        this.teamGuest = teamGuest;
    }

}
