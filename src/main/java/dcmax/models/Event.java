package dcmax.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String eventNameEng;

    @Column(length = 100)
    private String eventNameChn;

    @Column(nullable = false)
    private Date eventStartTime = new Date();

    @Column(nullable = false)
    private Date eventEndTime = new Date();

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date lastUpdatedTime = new Date();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User organizer;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EventType EventTypes;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EventLocation eventLocation;
}
