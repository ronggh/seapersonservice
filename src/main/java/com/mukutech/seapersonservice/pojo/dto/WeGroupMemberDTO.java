package com.mukutech.seapersonservice.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-08-08
 */
@Getter
@Setter
public class WeGroupMemberDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer groupId;
    private Integer uid;
    private String userRole;
    @ApiModelProperty(value = "是否禁言")
    private String isForbidden;


}
