package dcmax.repositories;

import dcmax.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByActiveTrue(Pageable pageable);

    // without count
    List<Post> findByActiveTrue(boolean active, Pageable pageable);

//    @Query("SELECT p FROM Post p WHERE :tagCount = (SELECT COUNT(DISTINCT t.id) FROM Post p2 JOIN p2.tags t WHERE p.hidden = false and LOWER(t.name) in (:tags) and p = p2)")
//    Page<Post> findByTagsAndNotHidden(@Param("tags") Collection<String> tags, @Param("tagCount") Long tagCount, Pageable pageable);


    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.author ORDER BY p.lastUpdatedTime DESC")
    List<Post> findLatest5Posts(Pageable pageable);
}

