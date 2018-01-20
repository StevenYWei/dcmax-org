package dcmax.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fieldPositions")
public class FieldPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, unique = true)
    private String fieldPositionEng;
    //Striker
    //Middle-field
    //Defense

    @Column(length = 30, unique = true)
    private String fieldPositionChn;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "fieldPositions")
    private Set<User> users = new HashSet<>();

    public FieldPosition() {}

    public FieldPosition(String fieldPositionEng) {
        this.fieldPositionEng = fieldPositionEng;
    }

    public FieldPosition(String fieldPositionEng, String fieldPositionChn) {
        this.fieldPositionEng = fieldPositionEng;
        this.fieldPositionChn = fieldPositionChn;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFieldPositionEng() { return fieldPositionEng;}

    public void setFieldPositionEng(String fieldPositionEng) { this.fieldPositionEng = fieldPositionEng; }

    public String getFieldPositionChn() { return fieldPositionChn;}

    public void setFieldPositionChn(String fieldPositionChn) { this.fieldPositionChn = fieldPositionChn; }
}
