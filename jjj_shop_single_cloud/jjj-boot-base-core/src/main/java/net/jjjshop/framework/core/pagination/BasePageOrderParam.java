

package net.jjjshop.framework.core.pagination;

import com.baomidou.mybatisplus.core.metadata.OrderItem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 可排序查询参数对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "可排序查询参数对象")
public abstract class BasePageOrderParam extends BasePageParam {
    private static final long serialVersionUID = 57714391204790143L;

    @Schema(description = "排序")
    private List<OrderItem> pageSorts;

    public void defaultPageSort(OrderItem orderItem) {
        this.defaultPageSorts(Arrays.asList(orderItem));
    }

    public void defaultPageSorts(List<OrderItem> pageSorts) {
        if (CollectionUtils.isEmpty(pageSorts)) {
            return;
        }
        this.pageSorts = pageSorts;
    }

}
