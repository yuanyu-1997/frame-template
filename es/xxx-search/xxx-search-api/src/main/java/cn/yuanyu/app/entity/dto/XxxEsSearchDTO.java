package cn.yuanyu.app.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yuanyu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class XxxEsSearchDTO extends EsSearchDTO {
    /**
     * xxx的分词搜索
     */
    private String xxx;
}
