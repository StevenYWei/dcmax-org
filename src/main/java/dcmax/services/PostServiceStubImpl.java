package dcmax.services;

import dcmax.models.Post;
import dcmax.models.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceStubImpl implements PostService
{
    private List<Post> posts = new ArrayList<Post>()
    {{
        add(new Post(1L, "Post 1", "<p>This is Post 1. Line #1.</p><p>Line #2</p>", null));
        add(new Post(2L, "Post 2",
                "Post 2 content:<ul><li>line #1</li><li>line #2</li></p>",
                new User(10L, "zhangda", "Changhui", "Li")));
        add(new Post(3L, "Post 3", "<p>The post number 3 nice</p>",
                new User(10L, "weisw", "Shuowen", "Wei")));
        add(new Post(4L, "Post 4", "<p>Not interesting post</p>", null));
        add(new Post(5L, "Post 5", "<p>Just posting</p>", null));
    }};

    @Override
    public List<Post> findAll()
    {
        return this.posts;
    }

    @Override
    public List<Post> findLatest5()
    {
        return this.posts.stream()
                .sorted((a, b) -> b.getLastUpdateTime().compareTo(a.getLastUpdateTime()))
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Post findById(Long id)
    {
        return this.posts.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(Post post)
    {
        post.setId(this.posts.stream().mapToLong(
                p -> p.getId()).max().getAsLong() + 1);
        this.posts.add(post);
        return post;
    }

    @Override
    public Post edit(Post post)
    {
        for (int i = 0; i < this.posts.size(); i++)
        {
            if (Objects.equals(this.posts.get(i).getId(), post.getId()))
            {
                this.posts.set(i, post);
                return post;
            }
        }
        throw new RuntimeException("Post not found: " + post.getId());
    }

    @Override
    public void deleteById(Long id)
    {
        for (int i = 0; i < this.posts.size(); i++)
        {
            if (Objects.equals(this.posts.get(i).getId(), id))
            {
                this.posts.remove(i);
                return;
            }
        }
        throw new RuntimeException("Post not found: " + id);
    }
}
