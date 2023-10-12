package dev.zkffl0.NoticeBoardSelf.controller;

import dev.zkffl0.NoticeBoardSelf.Dto.CommentAddDto;
import dev.zkffl0.NoticeBoardSelf.domain.Comment;
import dev.zkffl0.NoticeBoardSelf.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<Comment> createComment (@RequestBody CommentAddDto infoDto) {

        try {
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(commentService.save(infoDto));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping ("/comment/{id}")
    public ResponseEntity<Optional<Comment>> getCommentById (@PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(commentService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    @PostMapping ("/comment/{id}")
    public ResponseEntity<Optional<Comment>> updateComment (@PathVariable("id") Long id, @RequestBody CommentAddDto infoDto) {

        try {
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(commentService.save(infoDto));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") Long id) {

        try {
            commentService.delete(id);
            ResponseEntity.noContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
