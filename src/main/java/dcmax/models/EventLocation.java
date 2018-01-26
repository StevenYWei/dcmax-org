package dcmax.models;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event_locations")
public class EventLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String locationNameEng;

    @Column(length = 100)
    private String locationNameChn;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String zipcode;

    @Column(length = 100)
    private String state;

    @Column(nullable = false)
    private Date lastUsedTime = new Date();

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date lastUpdatedTime = new Date();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "eventLocation")
    private Set<Event> events = new HashSet<Event>();

    public EventLocation() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getLocationNameEng(){return locationNameEng;}

    public void setLocationNameEng(String locationNameEng){
        this.locationNameEng = locationNameEng;
    }

    public String getLocationNameChn(){return locationNameChn;}

    public void setLocationNameChn(String locationNameChn){
        this.locationNameChn = locationNameChn;
    }

    public String getFullLocationAddress(){
        return address + ", " + city + ", " + state + ", " + zipcode;
    }

    public void setFullLocationAddress(String address, String city, String state, String zipcode){
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Date getLastUsedTime() { return lastUsedTime;}

    public void setLastUsedTime(Date lastUsedTime) { this.lastUsedTime = lastUsedTime;}

    public boolean isActive() { return isActive;}

    public void setActive(boolean status) { this.isActive = status;}

    public Date getCreateTime() { return createTime;}

    public void setCreateTime(Date createTime) { this.createTime = createTime;}

    public Date getLastUpdateTime() { return lastUpdatedTime;}

    public void setLastUpdateTime(Date lastUpdatedTime) { this.lastUpdatedTime = lastUpdatedTime; }


}
