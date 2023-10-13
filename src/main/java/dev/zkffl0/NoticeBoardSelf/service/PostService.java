package dev.zkffl0.NoticeBoardSelf.service;

import dev.zkffl0.NoticeBoardSelf.Dto.CommentByPost;
import dev.zkffl0.NoticeBoardSelf.domain.Comment;
import dev.zkffl0.NoticeBoardSelf.domain.Post;
import dev.zkffl0.NoticeBoardSelf.repository.CommentRepository;
import dev.zkffl0.NoticeBoardSelf.repository.PostRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PostService{

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    public Post save(Post post) {

       try {
           Post postData = Post.builder()
                   .title(post.getTitle())
                   .content(post.getContent())
                   .build();

           return postRepository.save(postData);
       } catch (Exception e) {
           e.printStackTrace();
       }

       return null;
    }
    public Optional<Post> findById(Long id) {

        try {
            Optional<Post> postData = postRepository.findById(id);
            if (postData.isPresent())
                return postData;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Optional<List<Comment>> getCommentsForPost(Long id) {

        Optional<Post> postData = postRepository.findById(id);
        if (postData.isEmpty()) {
            throw new RuntimeException("해당 정보는 존재하지 않습니다.");
        }

        List<Comment> comments = commentRepository.findByPost(postData.get());
        return Optional.ofNullable(comments);

//        try {
//            Optional<List<Comment>> CommentData = postRepository.findById(id);
//        }
    }

    public Post update(Long id, Post post) {

        try {
            Optional<Post> postData = postRepository.findById(id);
            if (postData.isPresent()) {
                Post _post = postData.get();
                _post.setTitle(post.getTitle());
                _post.setContent(post.getContent());
                postRepository.save(_post);
                return _post;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public void delete(Long id) {

        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Post> getAllList () {
        List<Post> postList = postRepository.findAll();
        return postList;
    }
}
