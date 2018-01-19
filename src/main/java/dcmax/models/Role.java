package dcmax.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String roleName;
    //member
    //captain
    //organizer
    //management

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getRoleName() { return roleName;}

    public void setRoleName(String roleName) { this.roleName = roleName; }
}
