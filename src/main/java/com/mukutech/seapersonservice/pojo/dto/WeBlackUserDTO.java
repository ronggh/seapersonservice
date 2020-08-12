package com.mukutech.seapersonservice.pojo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


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

    private Integer uid;
    private Integer blackUid;
    private String status;
    @ApiModelProperty(value = "模糊查询字符串，用来匹配被拉黑的用户昵称或真名")
    private String query;

}
