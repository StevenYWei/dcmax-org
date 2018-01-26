package dcmax.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User captain;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date lastUpdatedTime = new Date();

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

    public boolean isActive() { return isActive;}

    public void setActive(boolean status) { this.isActive = status;}

    public Date getCreateTime() { return createTime;}

    public void setCreateTime(Date createTime) { this.createTime = createTime;}

    public Date getLastUpdateTime() { return lastUpdatedTime;}

    public void setLastUpdateTime(Date lastUpdatedTime) { this.lastUpdatedTime = lastUpdatedTime; }

    @Override
    public String toString() {
        return "Team{" +
                ", team name='" + teamNameEng + '\'' +
                ", captain='" + captain.getFirstNameEng() + ' ' + captain.getLastNameEng()+ '\'' +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }

}


