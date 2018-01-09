package dcmax.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String eventName;

    @Column(nullable = false, length = 300)
    private String eventFullAddress;

    @Column(nullable = false)
    private Date eventStartTime = new Date();

    @Column(nullable = false)
    private Date eventEndTime = new Date();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User organizer;

    @Column(nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date lastUpdatedTime = new Date();
}
