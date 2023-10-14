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

        Long postId = infoDto.getPostId(); // infoDto에서 postId 가져옴
        Optional<Post> optionalPost = postRepository.findById(postId); // 이 postId로 데이터베이스에서 Post OptionalPost로 가져옴

        if (optionalPost.isEmpty()) {
            throw new RuntimeException("해당 정보는 존재하지 않습니다.");
        }

        Post post = optionalPost.get(); // optionalPost가 비어있지 않다면, 게시물을 post 변수에 할당

        Comment _comment = Comment.builder() // Comment 객체 생성
                .content(infoDto.getComment_content()) // infoDto에서 댓글 내용(comment_content) 사용
                .post(post) // post를 사용
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
            Optional<Comment> optionalComment = commentRepository.findById(id);
            if (optionalComment.isPresent()) {
                Comment _comment = optionalComment.get();

                if (comment.getContent() != null)
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

    public Long findPostIdByCommentId(Long id) {

        try {
        Optional<Comment> commentData = commentRepository.findById(id);

            if (commentData.isPresent()) {
                Comment _comment  = commentData.get();
                return _comment.getPost().getId();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}