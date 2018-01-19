package dcmax.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 60)
    private String passwordHash;

    @Column(nullable = false, length = 30, unique = true)
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

    @Column(nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date lastUpdatedTime = new Date();

    @OneToMany(mappedBy = "author")
    private Set<Post> posts = new HashSet<Post>();

    @OneToMany(mappedBy = "organizer")
    private Set<Event> events = new HashSet<Event>();

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() { return passwordHash; }

    public void setPasswordHash(String passHash) {
        this.passwordHash = passHash;
    }

    public String getPassword() { return passwordHash; }

    public void setPassword(String pass) {
        this.passwordHash = pass;
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

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() { return createTime;}

    public void setCreateTime(Date createTime) { this.createTime = createTime;}

    public Date getLastUpdateTime() { return lastUpdatedTime;}

    public void setLastUpdateTime(Date lastUpdatedTime) { this.lastUpdatedTime = lastUpdatedTime; }

    public Set<Post> getPosts() { return posts; }

    public void setPosts(Set<Post> posts) { this.posts = posts; }

    public User() { }

    public User(Long id, String username, String firstNameEng, String lastNameEng) {
        this.id = id;
        this.username = username;
        this.firstNameEng = firstNameEng;
        this.lastNameEng = lastNameEng;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' +
                ", First Name='" + firstNameEng + '\'' +
                ", Last Name='" + lastNameEng + '\'' + '}';
    }
}

