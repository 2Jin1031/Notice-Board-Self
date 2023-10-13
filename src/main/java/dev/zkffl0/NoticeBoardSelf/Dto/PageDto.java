package dev.zkffl0.NoticeBoardSelf.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PageDto {

    private int pageN; // 현재 페이지
    private int pageSize; // 보통 10: 한 화면에 보이는 글 목록의 글 개수
    private int totalPost; // 전체 글 개수

    private int startN; // 현재 페이지 글 목록의 시작 번호
    private int endN; // 마지막 페이지 글 목록의 마지막 번호
    private int startPage; // 페이지 이동 버튼의 시작 번호
    private int endPage; // 페이지 이동 버튼의 마지막 번호
    private int totalPage;

    public PageDto(int pageN, int pageSize, int totalCount) {
        this.pageSize = pageSize;
        this.totalPost = totalPost;

        totalPage =  (totalPost - 1) / pageSize + 1; // 1 ~ 10 게시물은 1페이지, 11 ~ 20 게시물은 2페이지에

        this.pageN = (pageN < 1) ? 1 : pageN;
        this.pageN = (pageN > totalPage) ? totalPage : pageN;

        // 현재 페이지 6페이지라 가정, pageN = 6
        startN = (this.pageN - 1) * pageSize + 1; // 6페이지 글의 시작 번호는 51 -> (6 - 1) * 10 + 1 = 51
        endN = startN + (pageSize - 1); // 6페이지의 글의 마지막 번호는 60 -> 51 + 9 = 60
        this.endN = (this.endN > totalCount) ? totalPost : this.endN;

        startPage = (this.pageN - 1) / 10 * 10 + 1;

        endPage = (startPage + 9);
        this.endPage = (this.endPage > totalPage) ? totalPage : this.endPage;
    }
}
