package cn.yuanyu.db.vo;

import cn.yuanyu.db.pojo.OperationLog;
import lombok.Data;

import java.util.List;

/**
 * 封装分页信息
 *
 * @author yuanyu
 */
@Data
public class PageResult {
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 数据列表
     */
    private List<OperationLog> dataList;

    public PageResult() {

    }

    public PageResult(Long total, List<OperationLog> dataList) {
        this.total = total;
        this.dataList = dataList;
    }
}
