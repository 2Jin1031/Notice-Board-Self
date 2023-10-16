package dev.zkffl0.NoticeBoardSelf.controller;

import dev.zkffl0.NoticeBoardSelf.Dto.CommentContentByPostIdDto;
import dev.zkffl0.NoticeBoardSelf.domain.Comment;
import dev.zkffl0.NoticeBoardSelf.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment") // 댓글 생성하기 -> ok
    public ResponseEntity<Comment> createComment(@RequestBody CommentContentByPostIdDto infoDto) {
        Comment comment = commentService.save(infoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping ("/comment/{id}")  // 댓글 조회하기 -> ok
    public ResponseEntity<Optional<Comment>> getCommentById (@PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(commentService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @PostMapping ("/comment/{id}") // 댓글 업데이트하기 (댓글 내용 수정) -> ok
    public ResponseEntity<Optional<Comment>> updateComment (@PathVariable("id") Long id, @RequestBody Comment comment) {

        try {
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(commentService.update(id, comment));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @DeleteMapping("/comment/{id}") // 댓글 삭제하기 -> ok
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
