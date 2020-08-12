package com.mukutech.seapersonservice.entity;

import lombok.Data;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

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
public class WeBlackUser extends Model<WeBlackUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer uid;
    private Integer blackUid;
    private String status;
    private Date createtime;
    private Date updatetime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
