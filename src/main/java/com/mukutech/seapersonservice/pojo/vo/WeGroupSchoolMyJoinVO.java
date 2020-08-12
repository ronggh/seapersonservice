package com.mukutech.seapersonservice.pojo.vo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class WeGroupSchoolMyJoinVO extends WeGroupMyJoinVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer schoolId;
    // 学校英文名称
    private String schoolEnname;
    // 学校中文名称
    private String schoolCnname;
}