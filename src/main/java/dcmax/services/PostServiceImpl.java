package dcmax.services;

import dcmax.models.Post;
import dcmax.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserService userService;

//    @Override
//    public List<Post> findAll() {
//        return this.postRepo.findAll();
//    }

    @Override
    public List<Post> findLatest5() {
        return this.postRepo.findLatest5Posts(new PageRequest(0, 5));
    }

    @Override
    public Post findById(Long id) {
        return this.postRepo.findOne(id);
    }

    @Override
    public Post create(Post post) {
        return this.postRepo.save(post);
    }

    @Override
    public Post edit(Post post) {
        return this.postRepo.save(post);
    } //TODO

//    @Override
//    public void deleteById(Long id) {
//        this.postRepo.delete(id);
//    }

    @Override
    public Page<Post> getPostsPage(int pageNumber, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "lastUpdatedTime");

        if (userService.isAdmin())
            return postRepo.findAll(pageRequest);

        return postRepo.findByActiveTrue(pageRequest);
    }

    @Override
    public List<Post> getPostsList(int pageNumber, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "lastUpdatedTime");

        return postRepo.findByActiveTrue(true, pageRequest);
    }

    @Override
    public Post getPost(Long id) {
        return postRepo.findOne(id);
    }

    @Override
    public void deactivatePost(Long postId, boolean status) {
        Post post = getPost(postId);

        post.setActive(status);

        postRepo.saveAndFlush(post);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = getPost(postId);

        postRepo.delete(post);

        postRepo.flush();
    }
}
