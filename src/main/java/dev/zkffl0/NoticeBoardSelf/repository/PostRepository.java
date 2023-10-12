package dev.zkffl0.NoticeBoardSelf.repository;

import dev.zkffl0.NoticeBoardSelf.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
