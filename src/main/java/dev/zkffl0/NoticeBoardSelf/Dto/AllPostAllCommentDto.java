package dev.zkffl0.NoticeBoardSelf.Dto;

import dev.zkffl0.NoticeBoardSelf.domain.Comment;
import dev.zkffl0.NoticeBoardSelf.domain.Post;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AllPostAllCommentDto {

    private Long postId;

    private String title;

    private String content;

    private List<CommentDto> comments;

    // 추가로, Post와 Comment를 DTO로 변환하는 메서드나 생성자를 정의할 수 있습니다.
    public static AllPostAllCommentDto from(Post post, List<CommentDto> comments) {
        return AllPostAllCommentDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .comments(comments)
                .build();
    }

}
