package com.mukutech.seapersonservice.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 购物车入参类
 * </p>
 *
 * @author LMYOU
 * @since 2020-07-22
 */
@Data
public class FeedBackDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "反馈建议")
    private String feedBckContent;

    @ApiModelProperty(value = "图片路径数组")
    private List<String> picArr;
}
