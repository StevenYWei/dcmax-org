package dcmax.services;

import dcmax.models.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    List<Post> findLatest5();

    Post findById(Long id);

    Post create(Post post);

    Post edit(Post post);

//    void deleteById(Long id);

    Page<Post> getPostsPage(int pageNumber, int pageSize);

    List<Post> getPostsList(int pageNumber, int pageSize);

    Post getPost(Long id);

    void deactivatePost(Long postId, boolean status);

    void deletePost(Long postId);
}
