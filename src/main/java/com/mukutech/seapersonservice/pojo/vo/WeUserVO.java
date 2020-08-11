package com.mukutech.seapersonservice.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 社群管理员、成员简要信息
 */

@Data
public class WeUserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer uid;
    private String nickName;
    private String introduce;
    //
    private String headImg;
    private String verifyFlag;
    private Integer follow;
}
