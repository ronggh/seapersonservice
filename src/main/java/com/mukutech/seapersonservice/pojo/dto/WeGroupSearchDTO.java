package com.mukutech.seapersonservice.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
@Data
public class WeGroupSearchDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    @ApiModelProperty(value ="用户Id")
    private Integer uid;
    //
    @ApiModelProperty(value ="查询字符串")
    private String query;
}
