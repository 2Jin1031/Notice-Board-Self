package dev.zkffl0.NoticeBoardSelf.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentByPostDto {

    private int postId;
    private String comment;

}
