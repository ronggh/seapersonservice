package com.mukutech.seapersonservice.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 购物车入参类
 * </p>
 *
 * @author LMYOU
 * @since 2020-07-22
 */
@Data
public class RechargeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "操作类型，1-充值；2-提现")
    private Integer operType;

    @ApiModelProperty(value = "金额")
    private Integer money;
}
