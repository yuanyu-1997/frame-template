package cn.yuanyu.app.entity.dto;

import lombok.Data;

import java.util.HashMap;

/**
 * @author yuanyu
 */
@Data
public class EsSearchDTO {

    /**
     * 等值过滤
     */
    private HashMap<String, Object> termFilter;

    /**
     * or过滤查询
     */
    private HashMap<String, Object> orFilter;

    /**
     * not过滤查询
     */
    private HashMap<String, Object> notFilter;

    /**
     * 范围过滤,"(" 不包含,"[" 包含
     */
    private HashMap<String, Object> rangeFilter;

    /**
     * 结果包含的字段
     */
    private String[] fieldsInclude;

    /**
     * 结果不包含字段
     */
    private String[] fieldsExclude;

    /**
     * 排序
     */
    private HashMap<String, Object> sort;
}
