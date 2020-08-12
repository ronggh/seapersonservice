package com.mukutech.seapersonservice.service;


import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.entity.WeGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mukutech.seapersonservice.pojo.dto.WeGroupDTO;
import com.mukutech.seapersonservice.pojo.dto.WeGroupSearchDTO;


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
    ResponseEnvelope addWeGroup(WeGroupDTO DTO);

    // 02 - 更新社群
    ResponseEnvelope updateWeGroup(WeGroupDTO DTO);

    // 03 - 删除社群
    ResponseEnvelope deleteWeGroup(Integer groupId);


    // 04 - 搜索社群
    // 根据社群名称或描述，搜索社群列表，分页显示
    ResponseEnvelope searchGroup(WeGroupSearchDTO dto);

    // 05 - 我加入的社群列表，分页显示
    ResponseEnvelope getMyJoinGroupList(WeGroupSearchDTO dto);

    // 06 - 我管理的社群列表，分页显示
    ResponseEnvelope getMyManagerGroupList(WeGroupSearchDTO dto);


    // 07 - 我加入的社群详情， 包括获取社群成员简要信息
    ResponseEnvelope myJoinGroupDetail(Integer groupId, Integer uid);

    // 08 - 我管理的社群详情， 包括获取社群成员简要信息
    ResponseEnvelope myManagerGroupDetail(Integer groupId, Integer uid);

    // 09 - 设置社区消息提醒
    // memberTip:新增组员是否提醒，0：关闭；1：打开
    // noteTip:有新的发布是否提醒，0：关闭；1：打开
    ResponseEnvelope messageTips(Integer groupId,String memberTip,String noteTip);

    // 10 - 统计用户加入的社群信息
    ResponseEnvelope statsGroup(Integer uid);
}
