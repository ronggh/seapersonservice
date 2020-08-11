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
public class ShopCarDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "商品ID")
    private Long goodId;

    @ApiModelProperty(value = "商品名称")
    private String goodName;

    @ApiModelProperty(value = "商品数量")
    private Integer goodNum;

    @ApiModelProperty(value = "商品价格")
    private double goodPrice;
}
