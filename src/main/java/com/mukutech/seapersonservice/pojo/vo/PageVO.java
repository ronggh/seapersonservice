package com.mukutech.seapersonservice.pojo.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "总记录数")
    private Long total;
    @ApiModelProperty(value = "当前页数")
    private Long current;
    @ApiModelProperty(value = "每页条数")
    private Long size;
    @ApiModelProperty(value = "总页数")
    private Long pages;
    @ApiModelProperty(value = "数据记录")
    private List<T> records;

}
