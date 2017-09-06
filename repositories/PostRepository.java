package app.repositories;

import app.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post AS p WHERE p.comments.size = 0 ORDER BY p.id ASC")
    List<Post> getAllUncumentedPosts();

}
