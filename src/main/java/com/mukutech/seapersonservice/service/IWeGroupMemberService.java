package com.mukutech.seapersonservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.entity.WeGroupMember;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-08
 */
public interface IWeGroupMemberService extends IService<WeGroupMember> {
    // 01 - 加入社群
    ResponseEnvelope joinGroup(Integer uid, Integer groupId);

    // 02 - 退出社群
    ResponseEnvelope quitGroup(Integer uid, Integer groupId);

    // 03 - 成员禁言
    public ResponseEnvelope forbidden(Integer groupId, Integer uid);

    // 解禁
    // 04 - 解禁禁言
    public ResponseEnvelope removeForbidden(Integer groupId, Integer uid);

}
