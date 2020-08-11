package com.mukutech.seapersonservice.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class WeGroup extends Model<WeGroup> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "group_id", type = IdType.AUTO)
    private Integer groupId;
    private String groupName;
    private String groupDesc;
    private String applyReason;
    private String groupHeadImg;
    private String verifyFlag;
    private Integer createUid;
    private Integer schoolId;
    //private Integer area;
    //private Integer topic;
    private String memberTip;
    private String noteTip;
    @TableLogic(value = "1",delval = "0")
    private String status;
    private String group_mark;

    @TableField(fill = FieldFill.INSERT)
    private String createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatetime;


    @Override
    protected Serializable pkVal() {
        return this.groupId;
    }

}
