package com.mukutech.seapersonservice.pojo.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
@Data
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class WeGroupSearchResultVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "社群ID")
    private Integer groupId;
    @ApiModelProperty(value = "社群名称")
    private String groupName;
    private String groupDesc;
    private String groupHeadImg;
    // private Integer createUid;
    private Integer schoolId;

    // 加入社群人数
    @ApiModelProperty(value = "加入社群人数")
    private Integer memberCount;
    // 是否加入该社群
    @ApiModelProperty(value = "是否加入该社群")
    private Integer isJoined;
    // 在社群中的角色
    @ApiModelProperty(value = "在社群中的角色")
    private String userRole;

}