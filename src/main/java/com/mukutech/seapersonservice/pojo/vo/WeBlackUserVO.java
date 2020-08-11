package com.mukutech.seapersonservice.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

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
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeBlackUserVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer uid;

    private Integer blackUid;

    private String status;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    }