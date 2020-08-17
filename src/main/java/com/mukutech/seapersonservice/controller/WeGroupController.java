package com.mukutech.seapersonservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.pojo.dto.WeGroupDTO;
import com.mukutech.seapersonservice.pojo.dto.WeGroupMemberDTO;
import com.mukutech.seapersonservice.pojo.dto.WeGroupSearchDTO;
import com.mukutech.seapersonservice.pojo.vo.WeGroupAndMemberBriefVO;
import com.mukutech.seapersonservice.service.IWeGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
@Slf4j
@Api(tags = {"社群管理"})
@Controller
@RequestMapping("/seapersonservice/weGroup")
@ResponseBody
public class WeGroupController {

    @Autowired
    private IWeGroupService iWeGroupService;

    /**
     * 01-创建社群
     * 
     * @param dto
     * @return
     */
    @ApiOperation(position = 1, value = "01-创建社群", notes = "创建社群")
    @RequestMapping(value = "/addWeGroup", method = RequestMethod.POST)
    @ApiOperationSupport(
        ignoreParameters = {"dto.pageSize", "dto.currentPage", "dto.groupId", "dto.groupMark", "memberTip", "noteTip"})
    public ResponseEnvelope addWeGroup(@RequestBody WeGroupDTO dto) {

        return iWeGroupService.addWeGroup(dto);
    }

    /**
     * 02-修改社群基本信息
     * 
     * @param dto
     * @return
     */
    @ApiOperation(position = 2, value = "02-修改社群基本信息", notes = "修改社群基本信息")
    @RequestMapping(value = "/updateWeGroup", method = RequestMethod.POST)
    @ApiOperationSupport(ignoreParameters = {"dto.pageSize", "dto.currentPage", "dto.applyReason", "dto.createUid",
        "dto.groupMark", "dto.memberTip", "dto.noteTip"})
    public ResponseEnvelope updateWeGroup(@RequestBody WeGroupDTO dto) {

        return iWeGroupService.updateWeGroup(dto);
    }

    /**
     * 03-删除社群
     * 
     * @param
     * @return
     */
    @ApiOperation(position = 3, value = "03-删除社群", notes = "删除社群")
    @RequestMapping(value = "/deleteWeGroup", method = RequestMethod.POST)
    @ApiOperationSupport(includeParameters = {"dto.groupId"})
    public ResponseEnvelope deleteWeGroup(@RequestBody WeGroupDTO dto) {
        Integer groupId = dto.getGroupId();
        return iWeGroupService.deleteWeGroup(groupId);
    }

    // 04-搜索社群
    @ApiOperation(position = 4, value = "04-搜索社群", notes = "根据社群名称或描述，搜索结果列表分页显示")
    @RequestMapping(value = "/searchGroup", method = RequestMethod.POST)
    public ResponseEnvelope searchWeGroupListPage(@RequestBody WeGroupSearchDTO dto) {
        return iWeGroupService.searchGroup(dto);
    }

    // 05-我加入的社群列表
    @ApiOperation(position = 5, value = "05-我加入的社群列表", notes = "我加入的社群列表，分页显示")
    @RequestMapping(value = "/getMyJoinGroupList", method = RequestMethod.POST)
    @ApiOperationSupport(ignoreParameters = {"dto.query"})
    public ResponseEnvelope getMyJoinGroupList(@RequestBody WeGroupSearchDTO dto) {
        return iWeGroupService.getMyJoinGroupList(dto);
    }

    /**
     * 06-我管理的社群列表
     * 
     * @param dto
     * @return
     */
    @ApiOperation(position = 6, value = "06-我管理的社群列表", notes = "我管理的社群列表，分页显示")
    @RequestMapping(value = "/getMyManagerGroupList", method = RequestMethod.POST)
    @ApiOperationSupport(ignoreParameters = {"dto.query"})
    public ResponseEnvelope getMyManagerGroupList(@RequestBody WeGroupSearchDTO dto) {
        return iWeGroupService.getMyManagerGroupList(dto);
    }

    /**
     * 07-我加入的社群详情
     * 
     * @param dto
     * @return
     * @ApiOperationSupport(ignoreParameters = {"dto.id", "dto.userRole", "dto.isForbidden"})
     */
    @ApiOperation(position = 7, value = "07-我加入的社群详情", notes = "查询我加入的指定的某一个社群及成员信息")
    @RequestMapping(value = "/myJoinGroupDetail", method = RequestMethod.POST)
    @ApiOperationSupport(includeParameters = {"dto.groupId", "dto.uid"})
    public ResponseEnvelope<WeGroupAndMemberBriefVO> myJoinGroupDetail(@RequestBody WeGroupMemberDTO dto) {
        Integer groupId = dto.getGroupId();
        Integer uid = dto.getUid();
        return iWeGroupService.myJoinGroupDetail(groupId, uid);
    }

    //

    /**
     * 08-我管理的社群详情
     * 
     * @param
     * @param
     * @return
     */
    @ApiOperation(position = 8, value = "08-我管理的社群详情", notes = "查询我管理的指定的某一个社群及成员信息")
    @RequestMapping(value = "/myManagerGroupDetail", method = RequestMethod.POST)
    @ApiOperationSupport(includeParameters = {"dto.groupId", "dto.uid"})
    public ResponseEnvelope myManagerGroupDetail(@RequestBody WeGroupMemberDTO dto) {
        Integer groupId = dto.getGroupId();
        Integer uid = dto.getUid();
        return iWeGroupService.myManagerGroupDetail(groupId, uid);
    }

    /**
     * 09-设置社群消息提醒
     * 
     * @param dto
     * @return
     */
    @ApiOperation(position = 9, value = "09-设置社群消息提醒", notes = "09-设置社群消息提醒,0:关闭，1：打开")
    @RequestMapping(value = "/messageTips", method = RequestMethod.POST)
    @ApiOperationSupport(includeParameters = {"dto.groupId", "dto.memberTip", "dto.noteTip"})
    public ResponseEnvelope messageTips(@RequestBody WeGroupDTO dto) {
        Integer groupId = dto.getGroupId();
        String memberTip = dto.getMemberTip();
        String noteTip = dto.getNoteTip();
        return iWeGroupService.messageTips(groupId, memberTip, noteTip);
    }

    /**
     * 10-统计我加入的社群信息
     */
    @ApiOperation(position = 10, value = "10-统计我加入的社群信息", notes = "统计我加入的社群信息")
    @RequestMapping(value = "/statGroup", method = RequestMethod.POST)
    @ApiOperationSupport(includeParameters = {"dto.uid"})
    public ResponseEnvelope statGroup(@RequestBody WeGroupMemberDTO dto) {
        Integer uid = dto.getUid();
        return iWeGroupService.statsGroup(uid);
    }
}
