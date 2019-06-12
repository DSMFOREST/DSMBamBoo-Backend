package kim.jaehoon.dsmforest.repository;

import kim.jaehoon.dsmforest.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Posts, String> {

    Page<Posts> findAll(Pageable pageable);
    List<Posts> findByTitle(String title);
    Posts getById(Integer id);
    void deleteById(Integer id);
}