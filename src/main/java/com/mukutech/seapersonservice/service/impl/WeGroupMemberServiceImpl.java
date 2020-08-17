package com.mukutech.seapersonservice.service.impl;

import static com.mukutech.seapersonservice.common.utils.ResultVOUtil.returnSuccess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukutech.seapersonservice.common.utils.ResultVOUtil;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.entity.WeGroupMember;
import com.mukutech.seapersonservice.mapper.WeGroupMemberMapper;
import com.mukutech.seapersonservice.service.IWeGroupMemberService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-08
 */
@Slf4j
@Transactional
@Service
public class WeGroupMemberServiceImpl extends ServiceImpl<WeGroupMemberMapper, WeGroupMember>
    implements IWeGroupMemberService {

    @Autowired
    private WeGroupMemberMapper weGroupMemberMapper;

    /**
     * 01- 加入社群
     * 
     * @param uid
     * @param groupId
     * @return
     */
    @Override
    public ResponseEnvelope joinGroup(Integer uid, Integer groupId) {
        // 1. 如果已存在，则直接退出
        if (this.exists(uid, groupId) > 0) {
            return ResultVOUtil.returnSuccess();
        }

        // 2. 加入一条新数据
        WeGroupMember entity = new WeGroupMember();
        entity.setUid(uid);
        entity.setGroupId(groupId);
        // 状态：1-有效
        entity.setStatus("1");
        // 不禁言
        entity.setIsForbidden("0");
        // 2:普通成员
        entity.setUserRole("2");

        weGroupMemberMapper.insert(entity);
        return returnSuccess();
    }

    /**
     * 02- 退出社群
     * 
     * @param uid
     * @param groupId
     * @return
     */
    @Override
    public ResponseEnvelope quitGroup(Integer uid, Integer groupId) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("uid", uid);
        map.put("group_id", groupId);
        weGroupMemberMapper.deleteByMap(map);
        return ResultVOUtil.returnSuccess();
    }

    /**
     * 03 - 成员禁言
     * 
     * @param groupId
     * @param uid
     * @return
     */

    @Override
    public ResponseEnvelope forbidden(Integer groupId, Integer uid) {
        WeGroupMember weGroupMember = new WeGroupMember();
        weGroupMember.setIsForbidden("1");
        QueryWrapper<WeGroupMember> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", groupId).eq("uid", uid);
        weGroupMemberMapper.update(weGroupMember, wrapper);
        return ResultVOUtil.returnSuccess();
    }

    /**
     * 04 - 解禁禁言
     * 
     * @param groupId
     * @param uid
     * @return
     */

    @Override
    public ResponseEnvelope removeForbidden(Integer groupId, Integer uid) {
        WeGroupMember weGroupMember = new WeGroupMember();
        weGroupMember.setIsForbidden("0");
        QueryWrapper<WeGroupMember> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", groupId).eq("uid", uid);
        weGroupMemberMapper.update(weGroupMember, wrapper);
        return ResultVOUtil.returnSuccess();
    }

    /**
     * 查询指定的数据是否存在
     * 
     * @param uid
     * @param groupId
     * @return
     */
    private Integer exists(Integer uid, Integer groupId) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("uid", uid);
        map.put("group_id", groupId);
        map.put("status", "1");

        List<WeGroupMember> list = weGroupMemberMapper.selectByMap(map);
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return 1;
    }
}
