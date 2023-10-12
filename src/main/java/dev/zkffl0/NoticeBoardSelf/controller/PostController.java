package dev.zkffl0.NoticeBoardSelf.controller;

import dev.zkffl0.NoticeBoardSelf.Dto.CommentByPost;
import dev.zkffl0.NoticeBoardSelf.domain.Comment;
import dev.zkffl0.NoticeBoardSelf.domain.Post;
import dev.zkffl0.NoticeBoardSelf.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {

        try {
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(postService.save(post));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Optional<Post>> getPostbyId(@PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(postService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Optional<List<Comment>>> getCommentForPost(@PathVariable("id") Long id) {

        Optional<List<Comment>> comments = postService.getCommentsForPost(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/post/{id}")
    public ResponseEntity<Optional<Post>> updatePost(@PathVariable("id") Long id, @RequestBody Post post) {

        try {
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(postService.update(id, post));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<HttpStatus> deletePost (@PathVariable("id") Long id) {
        try {
            postService.delete(id);
            ResponseEntity.noContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
