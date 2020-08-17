package com.mukutech.seapersonservice.pojo.vo;

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
@Setter
@Getter
public class WeBlackUserVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    @ApiModelProperty(value = "拉黑的用户id")
    private Integer blackUid;
    @ApiModelProperty(value = "拉黑用户的昵称")
    private String nickname;
    @ApiModelProperty(value = "拉黑用户的真名")
    private String realName;
    @ApiModelProperty(value = "拉黑用户的头像")
    private String headImg;
}