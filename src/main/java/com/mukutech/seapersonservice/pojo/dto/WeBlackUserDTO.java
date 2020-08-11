package com.mukutech.seapersonservice.pojo.entity.dto;

import com.mukutech.seapersonservice.pojo.dto.BaseDTO;
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
public class WeBlackUserDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer uid;

    private Integer blackUid;

    private String status;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    }
