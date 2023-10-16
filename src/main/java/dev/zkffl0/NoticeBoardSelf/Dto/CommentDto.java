package dev.zkffl0.NoticeBoardSelf.Dto;

import dev.zkffl0.NoticeBoardSelf.domain.Comment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentDto {

    private Long commentId;

    private String content;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .build();
    }
}
