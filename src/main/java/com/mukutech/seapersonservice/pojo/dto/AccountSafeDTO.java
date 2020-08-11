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
public class AccountSafeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "绑定微信号")
    private String wechatNo;

    @ApiModelProperty(value = "绑定微博号")
    private String weiboNo;

    @ApiModelProperty(value = "绑定qq号")
    private String qqNo;

    @ApiModelProperty(value = "是否记住登录信息，1-是，0-否")
    private Integer isLockLoginInfo;
}
