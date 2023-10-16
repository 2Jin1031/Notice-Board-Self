package dev.zkffl0.NoticeBoardSelf.repository;

import dev.zkffl0.NoticeBoardSelf.Dto.AllPostAllCommentDto;
import dev.zkffl0.NoticeBoardSelf.domain.Comment;
import dev.zkffl0.NoticeBoardSelf.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<AllPostAllCommentDto> findByPost (Post post);
}
