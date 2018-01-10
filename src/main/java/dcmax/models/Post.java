package dcmax.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Lob
    @Column(nullable = false)
    private String body;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User author;

    @Column(nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date lastUpdatedTime = new Date();

    public Long getId() { return id; }

    public void setId(Long id)
    {
        this.id = id;
        setLastUpdateTime(new Date());
    }

    public String getTitle() { return title;}

    public void setTitle(String title)
    {
        this.title = title;
        setLastUpdateTime(new Date());
    }

    public String getBody() { return body;}

    public void setBody(String body)
    {
        this.body = body;
        setLastUpdateTime(new Date());
    }

    public User getAuthor() { return author;}

    public void setAuthor(User author)
    {
        this.author = author;
        setLastUpdateTime(new Date());
    }

    public Date getCreateTime() { return createTime;}

    public void setCreateTime(Date createTime) { this.createTime = createTime;}

    public Date getLastUpdateTime() { return lastUpdatedTime;}

    public void setLastUpdateTime(Date lastUpdatedTime) { this.lastUpdatedTime = lastUpdatedTime;}

    public Post() {}

    public Post(Long id, String title, String body, User author)
    {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    @Override
    public String toString()
    {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", author=" + author +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
