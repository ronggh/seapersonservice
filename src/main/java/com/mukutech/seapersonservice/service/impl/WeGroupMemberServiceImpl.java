package com.mukutech.seapersonservice.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukutech.seapersonservice.common.utils.ResultVOUtil;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.entity.WeGroupMember;
import com.mukutech.seapersonservice.mapper.WeGroupMemberMapper;
import com.mukutech.seapersonservice.service.IWeGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mukutech.seapersonservice.common.utils.ResultVOUtil.returnSuccess;

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
public class WeGroupMemberServiceImpl extends ServiceImpl<WeGroupMemberMapper, WeGroupMember> implements IWeGroupMemberService {

    @Autowired
    private WeGroupMemberMapper weGroupMemberMapper;

    // 01- 加入社群
    public ResponseEnvelope joinGroup(Integer uid, Integer groupId) {
        // 1. 如果已存在，则直接退出
        if(this.exists(uid,groupId)>0) return ResultVOUtil.returnSuccess();

        // 2. 加入一条新数据
        WeGroupMember entity = new WeGroupMember();
        entity.setUid(uid);
        entity.setGroupId(groupId);
        entity.setStatus("1");//状态：1-有效
        entity.setIsForbidden("0"); // 不禁言
        entity.setUserRole("2"); // 2:普通成员

        weGroupMemberMapper.insert(entity);
        return returnSuccess();
    }

    // 02- 退出社群
    public ResponseEnvelope quitGroup(Integer uid, Integer groupId) {
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("group_id",groupId);
        weGroupMemberMapper.deleteByMap(map);
        return ResultVOUtil.returnSuccess();
    }

    // 03 - 成员禁言/解禁
    // isForbidden：0,解禁；1：禁言
    public ResponseEnvelope forbiddenOrNot(Integer groupId,Integer uid,String isForbidden){
        // 条件更新
        WeGroupMember weGroupMember = this.selectBy(groupId,uid);
        // 非空则进行更新
        if(weGroupMember != null){
            weGroupMember.setIsForbidden(isForbidden);
            weGroupMemberMapper.updateById(weGroupMember);
        }
        return ResultVOUtil.returnSuccess();
    }


    // 获取指定的数据
    private WeGroupMember selectBy(Integer groupId,Integer uid){
        // 1. 初始化对象，构造查询条件
        Map<String,Object> map = new HashMap<>();
        map.put("group_id",groupId);
        map.put("uid",uid);
        map.put("status","1");

        // 查询
        List<WeGroupMember> list = weGroupMemberMapper.selectByMap(map);
        // 查到，则返回第一条数据即可
        if(list != null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
    // 查询指定的数据是否存在
    private Integer exists(Integer uid,Integer groupId){
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("group_id",groupId);
        map.put("status","1");

        List<WeGroupMember> list = weGroupMemberMapper.selectByMap(map);
        if(list == null || list.isEmpty()) return 0;
        return 1;
    }
}
