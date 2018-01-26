package dcmax.services;

import dcmax.models.Post;
import dcmax.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Override
    public List<Post> findAll() {
        return this.postRepo.findAll();
    }

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

    @Override
    public void deleteById(Long id) {
        this.postRepo.delete(id);
    }

}
