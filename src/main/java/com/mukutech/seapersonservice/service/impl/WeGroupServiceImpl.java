package com.mukutech.seapersonservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukutech.seapersonservice.common.utils.BeanCopyUtil;
import com.mukutech.seapersonservice.common.utils.ResultVOUtil;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.entity.WeGroup;
import com.mukutech.seapersonservice.entity.WeGroupMember;
import com.mukutech.seapersonservice.mapper.WeGroupMapper;
import com.mukutech.seapersonservice.mapper.WeGroupMemberMapper;
import com.mukutech.seapersonservice.pojo.dto.WeGroupDTO;
import com.mukutech.seapersonservice.pojo.dto.WeGroupSearchDTO;
import com.mukutech.seapersonservice.pojo.vo.*;
import com.mukutech.seapersonservice.service.IWeGroupService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
@Slf4j
@Transactional
@Service
public class WeGroupServiceImpl extends ServiceImpl<WeGroupMapper, WeGroup> implements IWeGroupService {

    @Autowired
    private WeGroupMapper weGroupMapper;
    @Autowired
    private WeGroupMemberMapper weGroupMemberMapper;

    /**
     * 01 - 创建社群
     * 
     * @param dto
     * @return
     */
    @Override
    public ResponseEnvelope addWeGroup(WeGroupDTO dto) {
        WeGroup entity = new WeGroup();
        BeanCopyUtil.copyPropertiesIgnoreNull(dto, entity);
        // 状态：1-有效
        entity.setStatus("1");
        // 审核状态
        entity.setVerifyFlag("0");
        // 有新增成员是否提醒，0：否
        entity.setMemberTip("0");
        // 有新增发布内容是否提醒，0：否
        entity.setNoteTip("0");
        weGroupMapper.insert(entity);
        return ResultVOUtil.returnSuccess();
    }

    /**
     * 02 - 修改社群基本信息
     * 
     * @param dto
     * @return
     */
    @Override
    public ResponseEnvelope updateWeGroup(WeGroupDTO dto) {
        WeGroup entity = new WeGroup();

        BeanCopyUtil.copyPropertiesIgnoreNull(dto, entity);
        entity.setStatus("1");
        weGroupMapper.updateById(entity);
        return ResultVOUtil.returnSuccess();
    }

    /**
     * 03 - 删除社群
     * 
     * @param groupId
     * @return
     */
    @Override
    public ResponseEnvelope deleteWeGroup(Integer groupId) {
        weGroupMapper.deleteById(groupId);
        return ResultVOUtil.returnSuccess();
    }

    /**
     * 04 - 搜索社群 根据社群名称或描述进行模糊查询，结果列表分页显示
     * 
     * @param dto
     * @return
     */
    @Override
    public ResponseEnvelope searchGroup(WeGroupSearchDTO dto) {
        // 1. 定义出分页的对象
        Page<WeGroup> page = new Page<>();
        page.setSize(dto.getPageSize());
        page.setCurrent(dto.getCurrentPage());

        // 2.定义查询条件
        QueryWrapper<WeGroup> queryWrapper = new QueryWrapper<>();
        // 指定查询哪些字段
        queryWrapper.select("group_id,group_name,group_desc,group_head_img,school_id");
        queryWrapper.eq("verify_flag", "1").like("group_name", dto.getQuery()).or().like("group_desc", dto.getQuery());
        // 3. 执行分页查询
        weGroupMapper.selectPage(page, queryWrapper);
        List<WeGroup> list = page.getRecords();

        // 4. 把结果处理成需要的接口形式
        List<WeGroupSearchResultVO> result = new ArrayList<>();

        for (WeGroup item : list) {
            WeGroupSearchResultVO vo = new WeGroupSearchResultVO();
            vo.setGroupId(item.getGroupId());
            vo.setGroupName(item.getGroupName());
            vo.setGroupDesc(item.getGroupDesc());
            vo.setGroupHeadImg(item.getGroupHeadImg());
            vo.setSchoolId(item.getSchoolId());
            // 计算加入该社群的人数
            Integer count = getMemberCount(item.getGroupId());
            if (null == count) {
                vo.setMemberCount(0);
            } else {
                vo.setMemberCount(count);
            }

            // 获取用户在社群中的角色
            String userRole = getMemberInfo(dto.getUid(), item.getGroupId());
            if (null == userRole) {
                vo.setIsJoined(0);
            } else {
                vo.setIsJoined(1);
                vo.setUserRole(userRole);
            }

            result.add(vo);
        }

        // 5. 封装成分页的形式
        Page<WeGroupSearchResultVO> resultPage = new Page<>();
        resultPage.setSize(dto.getPageSize());
        resultPage.setCurrent(dto.getCurrentPage());
        resultPage.setTotal(page.getTotal());
        resultPage.setSize(page.getSize());
        resultPage.setPages(page.getPages());

        resultPage.setRecords(result);
        return ResultVOUtil.returnSuccess(resultPage);
    }

    /**
     * 05 - 我加入的社群列表，分页显示
     * 
     * @param dto
     * @return
     */
    @Override
    public ResponseEnvelope getMyJoinGroupList(WeGroupSearchDTO dto) {
        // 1. 定义分页对象并赋值
        Page<WeGroupSearchResultVO> page = new Page<WeGroupSearchResultVO>();
        page.setSize(dto.getPageSize());
        page.setCurrent(dto.getCurrentPage());

        // 2. 执行多表查询结果
        List<WeGroupSearchResultVO> list = weGroupMapper.getMyJoinGroupList(page, dto.getUid());
        // 3. 处理结果成需要的格式
        if (list != null && list.size() > 0) {
            for (int index = 0; index < list.size(); index++) {
                WeGroupSearchResultVO item = list.get(index);
                if (item.getUserRole() != null) {

                    item.setIsJoined(1); // 设为加入
                    // 计算该社群加入人数
                    Integer count = getMemberCount(item.getGroupId());
                    if (null == count) {
                        item.setMemberCount(0);
                    } else {
                        item.setMemberCount(count);
                    }
                }
            }

            // 结果放在page中进行分页
            page.setRecords(list);
        }

        return ResultVOUtil.returnSuccess(page);
    }

    /**
     * 06 - 我管理的社群列表，分页显示
     * 
     * @param dto
     * @return
     */
    @Override
    public ResponseEnvelope getMyManagerGroupList(WeGroupSearchDTO dto) {
        Page<WeGroupSearchResultVO> page = new Page<>();
        page.setSize(dto.getPageSize());
        page.setCurrent(dto.getCurrentPage());

        // 多表查询结果
        List<WeGroupSearchResultVO> list = weGroupMapper.getMyManagerGroupList(page, dto.getUid());
        // 处理结果
        if (list != null && list.size() > 0) {
            for (WeGroupSearchResultVO item : list) {
                if (item.getUserRole() != null) {
                    item.setIsJoined(1); // 设为加入
                    // 计算该社群加入人数
                    Integer count = getMemberCount(item.getGroupId());
                    if (null == count) {
                        item.setMemberCount(0);
                    } else {
                        item.setMemberCount(count);
                    }
                }
            }

            // 结果放在page中进行分页
            page.setRecords(list);
        }

        return ResultVOUtil.returnSuccess(page);
    }

    /**
     * 07 - 我加入的社群详情况，包括成员简要信息
     * 
     * @param groupId
     * @param uid
     * @return
     */
    @Override
    public ResponseEnvelope myJoinGroupDetail(Integer groupId, Integer uid) {
        // 1. 初始化返回结果
        WeGroupAndMemberBriefVO vo = new WeGroupAndMemberBriefVO();
        // 2. 获取社群基本信息
        WeGroup weGroup = this.selectOne(groupId);
        vo.setGroupId(groupId);
        vo.setGroupName(weGroup.getGroupName());
        vo.setGroupDesc(weGroup.getGroupDesc());
        vo.setGroupHeadImg(weGroup.getGroupHeadImg());

        // 3. 获取社群管理员信息
        List<WeUserVO> admins = weGroupMapper.getMemberBrief(groupId, "1");
        // 处理关注关系
        admins = this.processFollow(uid, admins);
        vo.setAdmins(admins);
        // 4. 获取社群成员信息
        List<WeUserVO> members = weGroupMapper.getMemberBrief(groupId, "2");
        // 处理关注关系
        members = this.processFollow(uid, members);
        vo.setMembers(members);

        // 5. 封装结果，返回
        return ResultVOUtil.returnSuccess(vo);
    }

    /**
     * 08 - 我管理的社群详情， 包括获取社群成员简要信息
     * 
     * @param groupId
     * @param uid
     * @return
     */
    @Override
    public ResponseEnvelope myManagerGroupDetail(Integer groupId, Integer uid) {
        // 0. 先判断uid是否为该社群的管理员
        Integer isManager = weGroupMemberMapper.isManager(uid, groupId);
        // 不是管理，直接返回空
        if (isManager <= 0) {
            return ResultVOUtil.returnSuccess();
        }
        // 1. 初始化返回结果
        WeGroupAndMemberBriefVO vo = new WeGroupAndMemberBriefVO();
        // 2. 获取社群基本信息
        WeGroup weGroup = this.selectOne(groupId);
        vo.setGroupId(groupId);
        vo.setGroupName(weGroup.getGroupName());
        vo.setGroupDesc(weGroup.getGroupDesc());
        vo.setGroupHeadImg(weGroup.getGroupHeadImg());
        // 增加两个提醒设置字段
        vo.setMemberTip(weGroup.getMemberTip());
        vo.setNoteTip(weGroup.getNoteTip());

        // 3. 获取社群管理员信息
        List<WeUserVO> admins = weGroupMapper.getMemberBrief(groupId, "1");
        // 处理关注关系
        admins = this.processFollow(uid, admins);
        vo.setAdmins(admins);
        // 4. 获取社群成员信息
        List<WeUserVO> members = weGroupMapper.getMemberBrief(groupId, "2");
        // 处理关注关系
        members = this.processFollow(uid, members);
        vo.setMembers(members);

        // 5. 封装结果，返回
        return ResultVOUtil.returnSuccess(vo);
    }

    /**
     * 09 - 设置社区消息提醒 memberTip:新增组员是否提醒，0：关闭；1：打开 noteTip:有新的发布是否提醒，0：关闭；1：打开
     * 
     * @param groupId
     * @param memberTip
     * @param noteTip
     * @return
     */
    @Override
    public ResponseEnvelope messageTips(Integer groupId, String memberTip, String noteTip) {
        WeGroup weGroup = weGroupMapper.selectById(groupId);
        if (!StringUtils.isEmpty(memberTip)) {
            weGroup.setMemberTip(memberTip);
        }
        if (!StringUtils.isEmpty(noteTip)) {
            weGroup.setNoteTip(noteTip);
        }
        weGroupMapper.updateById(weGroup);

        return ResultVOUtil.returnSuccess();
    }

    /**
     * 10 - 社群首页统计信息
     * 
     * @param uid
     * @return
     */
    @Override
    public ResponseEnvelope statsGroup(Integer uid) {
        // 1. 初始化变量
        WeGroupStatsVO weGroupStatsVO = new WeGroupStatsVO();

        // 2. 获取我加入的非学校社群数量和HeadImg
        Integer joinNum = weGroupMapper.statsJoinGroupNum(uid);
        WeGroupMyJoinVO weGroupMyJoinVO = new WeGroupMyJoinVO();
        weGroupMyJoinVO.setGroupNum(joinNum);
        List<WeGroupLogoVO> joinImgs = weGroupMapper.getJoinGroupHeadImg(uid);
        weGroupMyJoinVO.setGroupHeadImg(joinImgs);
        // 加入到结果集中
        weGroupStatsVO.setMyJoin(weGroupMyJoinVO);

        // 3. 获取我加入的学校社群
        List<WeGroupSchoolMyJoinVO> schools = weGroupMapper.getJoinSchoolGroup(uid);
        if (schools != null && !schools.isEmpty()) {
            for (int index = 0; index < schools.size(); index++) {
                WeGroupSchoolMyJoinVO schoolVO = schools.get(index);
                Integer schoolId = schoolVO.getSchoolId();
                // 根据学校Id获取加入该学校的社群数量
                Integer num = weGroupMapper.getSchoolGroupNum(uid, schoolId);
                schoolVO.setGroupNum(num);
                // 根据学校Id获取加入该学校的社群的LOGO
                List<WeGroupLogoVO> imgs = weGroupMapper.getSchoolGroupHeadImg(schoolId);
                schoolVO.setGroupHeadImg(imgs);
            }
        }

        // 加入到结果集中
        weGroupStatsVO.setMySchools(schools);
        // 封装结果，返回
        return ResultVOUtil.returnSuccess(weGroupStatsVO);
    }

    /**
     * 根据groupId获取社群表we_group中的信息
     * 
     * @param groupId
     * @return
     */
    private WeGroup selectOne(Integer groupId) {
        WeGroup entity = new WeGroup();
        entity.setGroupId(groupId);
        QueryWrapper<WeGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(entity);
        return weGroupMapper.selectOne(queryWrapper);
    }

    /**
     * 根据社群ID,计算出该社群的成员数
     * 
     * @param groupId
     * @return
     */
    private Integer getMemberCount(Integer groupId) {
        WeGroupMember weGroupMember = new WeGroupMember();
        QueryWrapper<WeGroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", groupId);

        return weGroupMember.selectCount(queryWrapper);
    }

    /**
     * 获取指定用户在某个社群中的角色 "1" ： 管理员 "2" ： 普通成员
     * 
     * @param uid
     * @param groupId
     * @return
     */
    private String getMemberInfo(Integer uid, Integer groupId) {
        QueryWrapper<WeGroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,uid,group_id,user_role").eq("uid", uid).eq("group_id", groupId);
        WeGroupMember weGroupMember = weGroupMemberMapper.selectOne(queryWrapper);
        return weGroupMember == null ? null : weGroupMember.getUserRole();
    }

    /**
     * uid是否关注了list中的用户
     * 
     * @param uid
     * @param list
     * @return
     */
    private List<WeUserVO> processFollow(Integer uid, List<WeUserVO> list) {
        // 处理关注关系
        for (WeUserVO item : list) {
            // item.getUid 是we_user_relation表中的uuid
            Integer isFollowed = weGroupMapper.isFollowed(uid, item.getUid());
            if (isFollowed > 0) {
                item.setFollow(1);
            } else {
                item.setFollow(0);
            }

        }
        return list;
    }
}
