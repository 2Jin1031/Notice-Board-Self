package dev.zkffl0.NoticeBoardSelf.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentContentByPostIdDto {

    private String comment_content;

    private Long postId;
}
