package cn.yuanyu.app.entity.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author yuanyu
 */
public class XxxServiceWrapper {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ApiModel("XxxServiceWrapper.SearchDTO")
    public static class SearchDTO  {

        @NotNull
        @ApiModelProperty
        private XxxEsSearchDTO dto;

        @Min(1)
        @ApiModelProperty("页数")
        private Integer page;

        @Min(1)
        @ApiModelProperty("每页大小")
        private Integer size;
    }
}
