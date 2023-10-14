package dev.zkffl0.NoticeBoardSelf.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentAddDto {
    private String comment_content;
    private Long postId;
}
