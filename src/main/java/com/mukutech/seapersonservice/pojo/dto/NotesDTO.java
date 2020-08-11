package com.mukutech.seapersonservice.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 发布笔记入参类
 * </p>
 *
 * @author LMYOU
 * @since 2020-07-22
 */
@Data
public class NotesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "笔记ID")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "社群ID")
    private Long groupId;

    @ApiModelProperty(value = "发布标题")
    private String title;

    @ApiModelProperty(value = "发布内容")
    private String content;

    @ApiModelProperty(value = "图片路径数组")
    private List<String> picArr;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "话题ID")
    private Long topicId;

    @ApiModelProperty(value = "@同学的ID")
    private List<Long> userIdArr;

    @ApiModelProperty(value = "是否存为草稿，1-是，0-否")
    private Integer isDraft;

    @ApiModelProperty(value = "是否同步到个人主页，1-是，0-否")
    private Integer isAsyUserCenter;

}
