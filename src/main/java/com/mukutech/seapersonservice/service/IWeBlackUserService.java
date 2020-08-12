package com.mukutech.seapersonservice.service;

import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.entity.WeBlackUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mukutech.seapersonservice.pojo.dto.WeBlackUserDTO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
public interface IWeBlackUserService extends IService<WeBlackUser> {
    // 01 - 加入黑名单
    ResponseEnvelope addBlack(Integer uid,Integer blackUid);
    // 02 - 从黑名单中移除
    ResponseEnvelope removeBlack(Integer uid,Integer blackUid);
    // 03 - 显示指定用户的所有黑名单列表
    ResponseEnvelope getAllBlackListPage(WeBlackUserDTO dto);

    // 04 - 搜索指定用户的黑名单，按被拉黑人的昵称和真名模糊匹配
    ResponseEnvelope searchBlackListPage(WeBlackUserDTO dto);

}
