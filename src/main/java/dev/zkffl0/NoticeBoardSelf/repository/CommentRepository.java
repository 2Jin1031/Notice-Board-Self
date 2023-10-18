package dev.zkffl0.NoticeBoardSelf.repository;

import dev.zkffl0.NoticeBoardSelf.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<List<Comment>> findByPostId(Long postId);
}
