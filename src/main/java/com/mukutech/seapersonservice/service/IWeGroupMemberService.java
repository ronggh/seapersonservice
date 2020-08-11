package com.mukutech.seapersonservice.service;

import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.entity.WeGroupMember;
import com.baomidou.mybatisplus.extension.service.IService;


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
    public ResponseEnvelope joinGroup(Integer uid, Integer groupId);

    // 02 - 退出社群
    public ResponseEnvelope quitGroup(Integer uid, Integer groupId);

}
