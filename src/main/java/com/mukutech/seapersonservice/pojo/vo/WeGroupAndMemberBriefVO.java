package com.mukutech.seapersonservice.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 社群详细情况，包括管理员信息列表和成员信息列表
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeGroupAndMemberBriefVO implements Serializable {
    private static final long serialVersionUID = 1L;
    // 社群基本信息
    private Integer groupId;
    private String groupName;
    private String groupDesc;
    private String groupHeadImg;

    // 下面的两个字段在社群管理中使用
    // 分别是组员新增提醒和发布新笔记提醒设置，0：关闭，1：打开
    private String memberTip;
    private String noteTip;

    // 社群管理员信息列表
    private List<WeUserVO> admins;
    // 社群成员信息列表
    private List<WeUserVO> members;
}