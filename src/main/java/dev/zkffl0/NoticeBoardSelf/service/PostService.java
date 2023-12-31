package dev.zkffl0.NoticeBoardSelf.service;

import dev.zkffl0.NoticeBoardSelf.Dto.CommentsByPostDto;
import dev.zkffl0.NoticeBoardSelf.Dto.CommentDto;
import dev.zkffl0.NoticeBoardSelf.Dto.PostTitleDto;
import dev.zkffl0.NoticeBoardSelf.domain.Comment;
import dev.zkffl0.NoticeBoardSelf.domain.Post;
import dev.zkffl0.NoticeBoardSelf.repository.CommentRepository;
import dev.zkffl0.NoticeBoardSelf.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
//           builder은 아래 주석과 동일한 결과를 생성하지만 유연하게 데이터 가공 가능
//           Post postData = new Post();
//           postData.setTitle(post.getTitle());
//           postData.setContent(post.getContent());

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

    public CommentsByPostDto getCommentsForPost(Long postId) {

        Optional<Post> postData = postRepository.findById(postId);
        if (postData.isEmpty()) {
            throw new RuntimeException("해당 정보는 존재하지 않습니다.");
        }

        Optional<List<Comment>> optionalCommentList = commentRepository.findByPostId(postId);
        if (optionalCommentList.isEmpty()) {
            throw new RuntimeException("해당 게시물에는 댓글이 존재하지 않습니다.");
        }

        Post post = postData.get();
        List<Comment> commentList = optionalCommentList.get();

        List<CommentDto> commentDtoList = commentList.stream()
                .map(comment -> CommentDto.from(comment))
                .collect(Collectors.toList());

        CommentsByPostDto commentsByPostDto = CommentsByPostDto.from(post, commentDtoList);

        return commentsByPostDto;
    }

    public Post update(Long id, Post post) {

        try {
            Optional<Post> postData = postRepository.findById(id);
            if (postData.isPresent()) {
                Post _post = postData.get();

                if (post.getTitle() != null)
                    _post.setTitle(post.getTitle());
                if (post.getContent() != null)
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

    public List<PostTitleDto> getPostTitleList() {
        List<Post> postList = postRepository.findAll(); // 데이터베이스에서 모든 Post 객체를 가져와서 postlist 에 저장
        List<PostTitleDto> postTitleDtoList = new ArrayList<>(); // PostTitleDto 객체를 저장할 빈 ArrayList를 만듬 -> 이 목록은 게시물의 제목을 저장

        for (Post post : postList) { // postList에 있는 각 Post 객체에 대해 반복
            PostTitleDto postTitleDto = new PostTitleDto(); // 각 게시물에 대한 새로운 PostTitleDto 객체 생성
            postTitleDto.setPostTitle(post.getTitle()); // 현재 처리 중인 게시물의 제목을 PostTitleDto 객체에 설정
            postTitleDtoList.add(postTitleDto); // 완성된 PostTitleDto 객체를 postTitleDtoList 목록에 반환
        }
        return postTitleDtoList;
    }
}
