package dcmax.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 60)
    private String passwordHash;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(length = 20)
    private String middleName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, length = 20, unique = true)
    private String email;

    @Column(nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date lastUpdatedTime = new Date();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    @OneToMany(mappedBy = "author")
    private Set<Post> posts = new HashSet<Post>();

    @OneToMany(mappedBy = "organizer")
    private Set<Event> events = new HashSet<Event>();

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username;}

    public void setUsername(String username)
    {
        this.username = username;
        setLastUpdateTime(new Date());
    }

    public String getPasswordHash() { return passwordHash; }

    public void setPasswordHash(String passHash)
    {
        this.passwordHash = passHash;
        setLastUpdateTime(new Date());
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
        setLastUpdateTime(new Date());
    }

    public String getMiddleName() { return middleName; }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
        setLastUpdateTime(new Date());
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
        setLastUpdateTime(new Date());
    }

    public String getEmail() { return lastName; }

    public void setEmail(String email)
    {
        this.lastName = lastName;
        setLastUpdateTime(new Date());
    }

    public Date getCreateTime() { return createTime;}

    public void setCreateTime(Date createTime) { this.createTime = createTime;}

    public Date getLastUpdateTime() { return lastUpdatedTime;}

    public void setLastUpdateTime(Date lastUpdatedTime) { this.lastUpdatedTime = lastUpdatedTime; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public Set<Post> getPosts() { return posts; }

    public void setPosts(Set<Post> posts) { this.posts = posts; }

    public User() { }

    public User(Long id, String username, String firstName, String lastName)
    {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString()
    {
        return "User{" + "id=" + id + ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' + '}';
    }
}

