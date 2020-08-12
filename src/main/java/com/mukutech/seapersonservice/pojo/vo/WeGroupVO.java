package com.mukutech.seapersonservice.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeGroupVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer groupId;

    private String groupName;

    private String groupDesc;

    private String applyReason;

    private String groupHeadImg;

    private String verifyFlag;

    private Integer createUid;

    private Integer schoolId;

    //private Integer area;

   // private Integer topic;

    private String memberTip;

    private String noteTip;

    private String status;
    private String group_mark;

    private Date createtime;

    private Date updatetime;

    }