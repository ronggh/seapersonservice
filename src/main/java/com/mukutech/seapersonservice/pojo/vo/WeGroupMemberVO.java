package com.mukutech.seapersonservice.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-08
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeGroupMemberVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer groupId;

    private Integer uid;

    private String userRole;

    private String isForbidden;

    private String status;

    private String createtime;

    private String updatetime;

    }