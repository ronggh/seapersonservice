package com.mukutech.seapersonservice.pojo.vo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class WeGroupMyJoinVO implements Serializable {
    private static final long serialVersionUID = 1L;
    // 加入的社群数量
    private Integer groupNum;
    private List<WeGroupLogoVO> groupHeadImg;
}