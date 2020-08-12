package com.mukutech.seapersonservice.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 社群管理员、成员简要信息
 */

@Data
// 通过这个注解可以将空的值过滤，这样，isForbidden在我加入的社群详情中可以不显示
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeUserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer uid;
    private String nickName;
    private String introduce;
    //
    private String headImg;
    private String verifyFlag;
    private Integer follow;

    // 下面这个字段在社群管理详情中使用
    // 是否被禁言，0：否，1：是
    private String isForbidden;

}
