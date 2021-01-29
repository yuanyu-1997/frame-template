package cn.yuanyu.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

/**
 * @author yuanyu
 */
public class XxxWrapper {


    // https://www.cnblogs.com/Terry-Wu/p/8134732.html

    //@NotNull:     //CharSequence、Collection、Map 和 Array 对象不能是 null, 但可以是空集（size = 0）。
    //@NotEmpty:    //CharSequence, Collection, Map 和 Array 对象不能是 null 并且相关对象的 size 大于 0。
    //@NotBlank:    //String 不是 null 且去除两端空白字符后的长度（trimmed length）大于 0。


    //{
    //  "array": [],
    //  "charSequence": "",
    //  "collection": [],
    //  "map": {}
    //}
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ApiModel("XxxWrapper.NotNullDTO")
    public static class NotNullDTO {
        @NotNull
        private String charSequence;
        @NotNull
        private List<String> collection;
        @NotNull
        private Map<String, String> map;
        @NotNull
        private String[] array;
    }

    //{
    //    "array": ["张三","李四","王五"],
    //    "charSequence": "高一",
    //    "collection": ["年龄","身高","体重"],
    //    "map": {
    //        "age": "18",
    //		"hubby": "篮球"
    //    }
    //}

    //{
    //    "array": [null],
    //    "charSequence": "1",
    //    "collection": [null],
    //    "map": {"": ""}
    //}
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ApiModel("XxxWrapper.NotEmpty")
    public static class NotEmptyDTO {
        @NotEmpty
        private String charSequence;

        @NotEmpty
        private List<String> collection;

        @NotEmpty
        private Map<String, String> map;

        @NotEmpty
        private String[] array;
    }

    //@NotBlank   用在String上面
    //{
    //  "charSequence": ""
    //}
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ApiModel("XxxWrapper.NotBlankDTO")
    public static class NotBlankDTO {
        @NotBlank
        private String charSequence;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ApiModel("XxxWrapper.XxxMapSearchDTO")
    public static class XxxMapSearchDTO {

        @NotNull
        @Min(1)
        @ApiModelProperty(name = "页码", required = true)
        private Integer page;

        @NotNull
        @Min(1)
        @Max(1000)
        @ApiModelProperty(name = "页面大小", required = true)
        private Integer size;

        @NotNull
        @ApiModelProperty(name = "查询条件", required = true)
        private Map<String, String> search;

        @ApiModelProperty(name = "返回字段列表", required = true)
        @NotEmpty
        private List<String> fieldList;

        @ApiModelProperty(name = "排序", required = true)
        @NotEmpty
        private List<String> sortList;
    }


}
