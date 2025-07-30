package com.sotsamban.guesthouse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    private Pagination pagination;

    public static <T> PageResponse<T> of(List<T> content, int page, int size, long totalElements, int totalPages) {
        Pagination pagination = new Pagination(page, size, totalElements, totalPages);
        return new PageResponse<>(content, pagination);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pagination {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean first;
        private boolean last;

        public Pagination(int page, int size, long totalElements, int totalPages) {
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.first = page == 0;
            this.last = page == totalPages - 1;
        }
    }
}
