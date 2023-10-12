package dev.zkffl0.NoticeBoardSelf.service;

import dev.zkffl0.NoticeBoardSelf.Dto.CommentAddDto;
import dev.zkffl0.NoticeBoardSelf.domain.Comment;
import dev.zkffl0.NoticeBoardSelf.domain.Post;
import dev.zkffl0.NoticeBoardSelf.repository.CommentRepository;
import dev.zkffl0.NoticeBoardSelf.repository.PostRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor // 어노테이션
public class CommentService {

    private final CommentRepository commentRepository; // private final
    private final PostRepository postRepository;

    public Comment save(CommentAddDto infoDto) {

        Long postId = infoDto.getPostId();
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            throw new RuntimeException("해당 정보는 존재하지 않습니다.");
        }

        Comment _comment = Comment.builder()
                .content(infoDto.getComment_content()) // infoDto
                .post(optionalPost.get())
                .build();

        try {
            commentRepository.save(_comment);
            return _comment;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Optional<Comment> findById(Long id) {

        try {
            Optional<Comment> commentData = commentRepository.findById(id);
            if (commentData.isPresent())
                return commentData;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public Comment update(Long id, Comment comment) {

        try {
            Optional<Comment> commentData = commentRepository.findById(id);
            if (commentData.isPresent()) {
                Comment _comment = commentData.get();
                _comment.setContent(comment.getContent());
                commentRepository.save(_comment);
                return _comment;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void delete(Long id) {

        try {
            commentRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Comment> findByPost(Post post) {
        return commentRepository.findByPost(post);
    }
}