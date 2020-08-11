package com.mukutech.seapersonservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.entity.WeGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mukutech.seapersonservice.pojo.dto.WeGroupDTO;
import com.mukutech.seapersonservice.pojo.dto.WeGroupSearchDTO;
import com.mukutech.seapersonservice.pojo.vo.WeGroupSearchResultVO;
import com.mukutech.seapersonservice.pojo.vo.WeUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
public interface IWeGroupService extends IService<WeGroup> {
    // 01 - 创建社群
    public ResponseEnvelope addWeGroup(WeGroupDTO DTO);

    // 02 - 更新社群
    public ResponseEnvelope updateWeGroup(WeGroupDTO DTO);

    // 03 - 删除社群
    public ResponseEnvelope deleteWeGroup(Integer groupId);


    // 04 - 搜索社群
    // 根据社群名称或描述，搜索社群列表，分页显示
    public ResponseEnvelope searchGroup(WeGroupSearchDTO dto);

    // 05 - 我加入的社群列表，分页显示
    public ResponseEnvelope getMyJoinGroupList(WeGroupSearchDTO dto);

    // 06 - 我管理的社群列表，分页显示
    public ResponseEnvelope getMyManagerGroupList(WeGroupSearchDTO dto);


    // 07- 我加入的社群详情， 包括获取社群成员简要信息
    public ResponseEnvelope myJoinGroupDetail(Integer groupId, Integer uid);

}
