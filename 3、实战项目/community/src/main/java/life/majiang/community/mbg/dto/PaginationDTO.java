package life.majiang.community.mbg.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {

    private boolean showFirstPage;                      // 是否显示头页标签 <<
    private boolean showLastPage;                       // 是否显示尾页标签 >>
    private boolean showPrevPage;                       // 是否显示前一页 <
    private boolean showNextPage;                       // 是否显示下一页 >
    private Integer currentPage;                        // 当前页是第几页
    private Integer totalPages;                         // 一共有多少页
    private List<Integer> pages = new ArrayList<>();    // 显示页码
    private List<Object> data;
    private Integer totalCount;

    /**
     *
     * @param totalCount    总数据条数
     * @param page          第几页
     * @param size          每页数据条数
     */
    public void init(Integer totalCount, Integer page, Integer size) {
        this.totalCount = totalCount;
        this.currentPage = page;
        this.totalPages = (totalCount % size == 0) ? totalCount/size : totalCount/size + 1;
        this.showFirstPage = (page - 3 > 1);
        this.showLastPage = (totalPages - page > 3);
        this.showPrevPage = (page != 1);
        this.showNextPage = (page != totalPages);

        /**
         * 假设page为4，总页数为10
         * 则页码列表应为 1,2,3,4,5,6,7
         */
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPages) {
                pages.add(page + i);
            }
        }
    }

}
