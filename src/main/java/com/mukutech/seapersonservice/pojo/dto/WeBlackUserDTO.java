package com.mukutech.seapersonservice.pojo.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
@Getter
@Setter
public class WeBlackUserDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Integer uid;
    @ApiModelProperty("拉黑的用户id")
    private Integer blackUid;

    @ApiModelProperty(value = "模糊查询字符串，用来匹配被拉黑的用户昵称或真名")
    private String query;

}
