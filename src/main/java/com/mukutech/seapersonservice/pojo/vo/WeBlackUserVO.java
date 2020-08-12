package com.mukutech.seapersonservice.pojo.vo;


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
@Setter
@Getter
public class WeBlackUserVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer uid;
    private Integer blackUid;
    private String nickname;
    private String realName;
    private String headImg;
}