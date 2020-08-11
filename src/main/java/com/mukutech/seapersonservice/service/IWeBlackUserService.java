package com.mukutech.seapersonservice.service;

import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.entity.WeBlackUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mukutech.seapersonservice.pojo.entity.dto.WeBlackUserDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
public interface IWeBlackUserService extends IService<WeBlackUser> {
public ResponseEnvelope searchWeBlackUserListPage(WeBlackUserDTO DTO);
public ResponseEnvelope searchWeBlackUserOne(Integer id);
public ResponseEnvelope addWeBlackUser(WeBlackUserDTO DTO);
public ResponseEnvelope updateWeBlackUser(WeBlackUserDTO DTO);
public ResponseEnvelope deleteWeBlackUser(Integer id);
        }
