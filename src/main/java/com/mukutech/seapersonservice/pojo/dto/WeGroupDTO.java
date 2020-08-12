package com.mukutech.seapersonservice.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class WeGroupDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "社群Id")
    private Integer groupId;

    private String groupName;

    private String groupDesc;

    private String applyReason;

    private String groupHeadImg;

    // private String verifyFlag;

    private Integer createUid;
    private String groupMark;

    //private Integer schoolId;
    //
    @ApiModelProperty(value = "添加成员是否提醒")
    private String memberTip;
    @ApiModelProperty(value = "发布新内容是否提醒")
    private String noteTip;

}
