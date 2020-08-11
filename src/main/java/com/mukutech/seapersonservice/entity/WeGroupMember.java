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
 * @since 2020-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WeGroupMember extends Model<WeGroupMember> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer groupId;
    private Integer uid;
    private String userRole;
    private String isForbidden;
    @TableLogic(value = "1",delval = "0")
    private String status;
    @TableField(fill = FieldFill.INSERT)
    private String createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatetime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
