package dev.zkffl0.NoticeBoardSelf.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentAddDto {
    private String comment_content;
    private Long postId;
}
