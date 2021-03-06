package dcmax.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    public interface CreateValidationGroup {}

    public interface ChangeEmailValidationGroup {}

    public interface ChangePasswordValidationGroup {}

    public interface ProfileInfoValidationGroup {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    @Size.List({
            @Size(min = 6, message = "Username too short", groups = {CreateValidationGroup.class}),
            @Size(max = 30, message = "Username too long", groups = {CreateValidationGroup.class})
    })
    @NotBlank(groups = {CreateValidationGroup.class})
    @Pattern(regexp = "^[\\p{L}0-9\\._\\- ]+$", groups = {CreateValidationGroup.class})
    private String username;

    @Column(nullable = false, length = 80)
    @Size.List({
            @Size(min = 6, message = "Password too short", groups = {CreateValidationGroup.class, ChangePasswordValidationGroup.class}),
            @Size(max = 80, message = "Password too long", groups = {CreateValidationGroup.class, ChangePasswordValidationGroup.class})
    })
    @NotBlank(groups = {CreateValidationGroup.class, ChangePasswordValidationGroup.class})
    private String password;

    @Column(length = 50, unique = true) // TODO: to add back: nullable = false -- removed temporarily for testing purpose
    @Email(groups = {CreateValidationGroup.class, ChangeEmailValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, ChangeEmailValidationGroup.class})
    private String email;

    @Column(length = 20)
    private String firstNameEng;

    @Column(length = 20)
    private String middleNameEng;

    @Column(length = 20)
    private String lastNameEng;

    @Column(length = 20)
    private String firstNameChn;

    @Column(length = 20)
    private String lastNameChn;

    @Column(length = 20)
    private String cellPhoneNumber;

    @Column(length = 20)
    private String homePhoneNumber;

    @Column
    private Boolean active = true;

    @Column(nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date lastUpdatedTime = new Date();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<Role>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_fieldPositions",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "fieldPosition_id")})
    private Set<FieldPosition> fieldPositions = new HashSet<FieldPosition>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "members")
    private Set<Team> teamMembers = new HashSet<Team>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "event_organizers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private Set<Event> eventOrganizers = new HashSet<Event>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "participants")
    private Set<Event> eventParticipants = new HashSet<Event>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author")
    private Set<Post> posts = new HashSet<Post>();

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "captain")
//    private Set<Team> teamCaptain = new HashSet<Team>();
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "viceCaptain")
//    private Set<Team> teamViceCaptain = new HashSet<Team>();

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstNameEng() { return firstNameEng; }

    public void setFirstNameEng(String firstNameEng) {
        this.firstNameEng = firstNameEng;
    }

    public String getMiddleNameEng() { return middleNameEng; }

    public void setMiddleNameEng(String middleNameEng) {
        this.middleNameEng = middleNameEng;
    }

    public String getLastNameEng() { return lastNameEng; }

    public void setLastNameEng(String lastNameEng) {
        this.lastNameEng = lastNameEng;
    }

    public String getFirstNameChn() { return firstNameChn; }

    public void setFirstNameChn(String firstNameChn) {
        this.firstNameChn = firstNameChn;
    }

    public String getLastNameChn() { return lastNameChn; }

    public void setLastNameChn(String lastNameChn) {
        this.lastNameChn = lastNameChn;
    }

    public String getCellPhoneNumber() { return cellPhoneNumber; }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getHomePhoneNumber() { return homePhoneNumber; }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreateTime() { return createTime;}

    public void setCreateTime(Date createTime) { this.createTime = createTime;}

    public Date getLastUpdatedTime() { return lastUpdatedTime;}

    public void setLastUpdatedTime(Date lastUpdatedTime) { this.lastUpdatedTime = lastUpdatedTime; }

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public boolean hasRole(String role) {
        final String finalRole = role;
        return getRoles().stream().anyMatch(r -> r.getRoleName().equals(finalRole));
    }

    public boolean isInTeam(Team team) {
        final Team finalTeam = team;
        return getTeamMembers().stream().anyMatch(t -> t.getId().equals(finalTeam.getId()));
    }

    public Set<Event> getEventParticipants() { return eventParticipants;}

    public void setEventParticipants(Set<Event> eventParticipants) {
        this.eventParticipants = eventParticipants;
    }

    public boolean isInEvent(Event event) {
        final Event finalEvent = event;
        return getEventParticipants().stream().anyMatch(r -> r.getId().equals(finalEvent.getId()));
    }

    public Set<Event> getEventOrganizers() { return eventOrganizers; }

    public void setEventOrganizers(Set<Event> eventOrganizers) { this.eventOrganizers = eventOrganizers; }

    public boolean isOrganizeEvent(Event event) {
        final Event finalEvent = event;
        return getEventOrganizers().stream().anyMatch(r -> r.getId().equals(finalEvent.getId()));
    }

//    public Set<Team> getTeamCaptain() { return teamCaptain; }
//
//    public void setTeamCaptain(Set<Team> teamCaptain) { this.teamCaptain = teamCaptain; }
//
//    public boolean isTeamCaptain(Team team) {
//        final Team finalTeam = team;
//        return getTeamCaptain().stream().anyMatch(t -> t.getId().equals(finalTeam.getId()));
//    }
//
//    public Set<Team> getTeamViceCaptain() { return teamViceCaptain; }
//
//    public void setTeamViceCaptain(Set<Team> teamViceCaptain) { this.teamViceCaptain = teamViceCaptain; }
//
//    public boolean isTeamViceCaptain(Team team) {
//        final Team finalTeam = team;
//        return getTeamViceCaptain().stream().anyMatch(t -> t.getId().equals(finalTeam.getId()));
//    }

    public Set<FieldPosition> getFieldPositions() { return fieldPositions; }

    public void setFieldPositions(Set<FieldPosition> fieldPositions) { this.fieldPositions = fieldPositions; }

    public Set<Team> getTeamMembers() { return teamMembers; }

    public void setTeamMembers(Set<Team> teamMembers) { this.teamMembers = teamMembers; }

    public boolean hasFieldPosition(String fieldPosition) {
        final String finalFieldPosition = fieldPosition;
        return getFieldPositions().stream().anyMatch(r -> r.getFieldPositionEng().equals(finalFieldPosition))
                || getFieldPositions().stream().anyMatch(r -> r.getFieldPositionChn().equals(finalFieldPosition));
    }

    public Set<Post> getPosts() { return posts; }

    public void setPosts(Set<Post> posts) { this.posts = posts; }

    public User() { }

    public User(String username, String password, String email, boolean activeStatus) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = activeStatus;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' +
                ", First Name='" + firstNameEng + '\'' +
                ", Last Name='" + lastNameEng + '\'' + '}';
    }
}

