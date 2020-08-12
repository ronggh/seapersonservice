package com.mukutech.seapersonservice.pojo.vo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class WeGroupStatsVO implements Serializable {
    private static final long serialVersionUID = 1L;
    // 我加入的社群统计（非学校）
    private WeGroupMyJoinVO myJoin;
    // 我加入的学校社群统计
    private List<WeGroupSchoolMyJoinVO> mySchools;
}