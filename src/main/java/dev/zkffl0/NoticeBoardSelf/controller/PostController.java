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

    @PostMapping("/post") // 게시글 생성하기 -> ok
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

    @GetMapping("/post/{id}") // 게시글 조회하기 -> ok
    public ResponseEntity<Optional<Post>> getPostbyId(@PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(postService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/{id}/comments") // 댓글들 조회하기(게시글 id로) -> ok
    public ResponseEntity<Optional<List<Comment>>> getCommentForPost(@PathVariable("id") Long id) {

        Optional<List<Comment>> comments = postService.getCommentsForPost(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/postAll-list") // 전체 게시글(제목 + 내용) 리스트 조회하기 -> ok
    public ResponseEntity<List<Post>> getPostAllList() {

        List<Post> post = postService.getAllList();
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

//    @GetMapping("/postTitle-list") // 전체 게시글(제목) 리스트 조회하기 -> ok
//    public ResponseEntity<List<Post>> getPostTitleList() {
//
//        List<Post> post = postService.getAllList();
//        return new ResponseEntity<>(post, HttpStatus.OK);
//    }
    @PostMapping("/post/{id}") // 게시글 업데이트하기 (게시글 제목 + 내용 수정) -> ok
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

    @DeleteMapping("/post/{id}") // 게시글 삭제하기 -> ok
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
