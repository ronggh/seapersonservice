package com.mukutech.seapersonservice.pojo.vo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class WeGroupLogoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer groupId;
    private String groupHeadImg;
}