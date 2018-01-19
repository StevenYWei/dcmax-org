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

    @Column(nullable = false, length = 50, unique = true)
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

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
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

