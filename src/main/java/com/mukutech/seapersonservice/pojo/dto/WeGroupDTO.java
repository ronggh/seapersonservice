package com.mukutech.seapersonservice.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
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

    @TableField(value = "group_id")
    private Integer groupId;

    private String groupName;

    private String groupDesc;

    private String applyReason;

    private String groupHeadImg;

   // private String verifyFlag;

    private Integer createUid;
    private String group_mark;

    //private Integer schoolId;

//    private Integer area;
//
//    private Integer topic;
//
//    private String memberTip;
//
//    private String noteTip;

  //  private String status;

//    private LocalDateTime createtime;
//
//    private LocalDateTime updatetime;

    }
