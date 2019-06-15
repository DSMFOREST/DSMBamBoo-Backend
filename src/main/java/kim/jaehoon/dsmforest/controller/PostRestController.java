package kim.jaehoon.dsmforest.controller;

import kim.jaehoon.dsmforest.domain.Posts;
import kim.jaehoon.dsmforest.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/post")
public class PostRestController {
    private PostRepository postRepository;

    public PostRestController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getBoards(@PageableDefault Pageable pageable) {
        Page<Posts> posts = postRepository.findAll(pageable);
        PageMetadata pageMetadata = new PageMetadata(pageable.getPageSize(), posts.getNumber(), posts.getTotalElements());
        PagedResources resources = new PagedResources<>(posts.getContent(), pageMetadata);
        resources.add(linkTo(methodOn(PostRestController.class).getBoards(pageable)).withSelfRel());
        return ResponseEntity.ok(resources);
    }

//    @PostMapping
//    public ResponseEntity<?> writePosts(@RequestBody PostsForm post, HttpServletRequest request) {
//        Posts saved = this.postRepository.save(new Posts(post.getTitle()));
//        postRepository.save(post);
//        return new ResponseEntity<>("{}", HttpStatus.CREATED);
//    }

    @PutMapping("/{idx}")
    public ResponseEntity<?> modifyPosts(@PathVariable("idx")Integer idx, @RequestBody Posts post) {
        Posts persistPost = postRepository.getById(idx);
        persistPost.update(post);
        postRepository.save(persistPost);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deletePosts(@PathVariable("idx") Integer idx) {
        postRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
