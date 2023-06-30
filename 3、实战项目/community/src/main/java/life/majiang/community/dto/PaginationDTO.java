package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showLastPage;
    private Integer page;
    private Integer totalPages;
    private List<Integer> pages = new ArrayList<>();

    /**
     *
     * @param totalCount   总数据量
     * @param page         当前数据页属于第几页
     * @param size         每页数据条数
     */
    public void setPagination(Integer totalCount, Integer page, Integer size) {
        this.page = page;
        // 若数据总数不满整数页则按整数页计算
        if (totalCount % size == 0) {
            this.totalPages = totalCount / size;
        } else {
            this.totalPages = totalCount / size + 1;
        }

        /**
         * 若数据不是第一页则要显示上一页按钮
         * 若数据不是最后一页则要显示下一页按钮
         * 若当前页前面有超过3页，则显示跳转到首页按钮
         * 若当前页后面有超过3页，则显示跳转到尾页按钮
          */
        this.showPrevious = !(page == 1);
        this.showNext = !(page == this.totalPages);
        this.showFirstPage = (page - 3 > 1);
        this.showLastPage = (totalPages - page > 3);

        // 分页导航栏显示列表数量
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
