package dev.zkffl0.NoticeBoardSelf.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostPages {

    private int totalPosts; // 전체 글의 개수
    private int currentPage; // 현재 페이지 (예시: 현재 6페이지라 가정)
    private int totalPages; // 전체 페이지 개수
    private int startPage; // 시작 페이지 번호 ('이전 [1] [2] [3] [4] [5] 다음' 일때 1을 의미)
    private int endPage; // 종료 페이지 번호 ('이전 [1] [2] [3] [4] [5] 다음' 일때 5를 의미)
    private int pagingCount; // 페이징의 개수
    private List<Post> post;

    public PostPages(int totalPosts, int currentPage, int size, int pagingCount, List<Post> post) {
        this.totalPosts = totalPosts;
        this.currentPage = currentPage;
        this.post = post;
        this.pagingCount = pagingCount;

        if (totalPosts == 0) {
            totalPages = 0;
            startPage = 0;
            endPage = 0;
        } else {
            totalPages = totalPosts / size;
            if (totalPosts % size > 0) {
                totalPages++;
            }

            startPage = currentPage / pagingCount * pagingCount + 1;
            if (currentPage % pagingCount == 0) {
                startPage -= pagingCount;
            }

            endPage = startPage + pagingCount - 1;
            if (endPage > totalPages) {
                endPage = totalPages;
            }
        }
    }

    public boolean hasNoPostPosts() {
        return this.totalPosts > 0;
    }

    public boolean hasPostPosts() {
        return this.totalPosts > 0;
    }
}