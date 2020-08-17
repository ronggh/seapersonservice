package com.mukutech.seapersonservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.pojo.dto.WeGroupMemberDTO;
import com.mukutech.seapersonservice.service.IWeGroupMemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-08
 */
@Slf4j
@Api(tags = {"社群成员管理"})
@Controller
@RequestMapping("/seapersonservice/weGroupMember")
@ResponseBody
public class WeGroupMemberController {

    @Autowired
    private IWeGroupMemberService iWeGroupMemberService;

    /**
     * 01- 用户加入社群
     * 
     * @param
     * @return
     */
    @ApiOperation(position = 1, value = "01 - 加入社群", notes = "用户加入社群")
    @RequestMapping(value = "/joinGroup", method = RequestMethod.POST)
    @ApiOperationSupport(
        ignoreParameters = {"dto.pageSize", "dto.currentPage", "dto.id", "dto.userRole", "dto.isForbidden"})
    public ResponseEnvelope joinGroup(@RequestBody WeGroupMemberDTO dto) {
        Integer uid = dto.getUid();
        Integer groupId = dto.getGroupId();
        return iWeGroupMemberService.joinGroup(uid, groupId);
    }

    /**
     * 02 - 用户退出社群
     * 
     * 
     * @return
     */
    @ApiOperation(position = 2, value = "02 - 退出社群", notes = "社群成员退出")
    @RequestMapping(value = "/quitGroup", method = RequestMethod.POST)
    @ApiOperationSupport(
        ignoreParameters = {"dto.pageSize", "dto.currentPage", "dto.id", "dto.userRole", "dto.isForbidden"})
    public ResponseEnvelope quitGroup(@RequestBody WeGroupMemberDTO dto) {
        Integer uid = dto.getUid();
        Integer groupId = dto.getGroupId();
        return iWeGroupMemberService.quitGroup(uid, groupId);
    }

    /**
     * 03 - 成员禁言
     * 
     * @param dto
     * @return
     */
    @ApiOperation(position = 3, value = "03 - 成员禁言", notes = "成员禁言")
    @RequestMapping(value = "/forbidden", method = RequestMethod.POST)
    @ApiOperationSupport(
        ignoreParameters = {"dto.pageSize", "dto.currentPage", "dto.id", "dto.userRole", "dto.isForbidden"})
    public ResponseEnvelope forbidden(@RequestBody WeGroupMemberDTO dto) {
        Integer groupId = dto.getGroupId();
        Integer uid = dto.getUid();
        return iWeGroupMemberService.forbidden(groupId, uid);
    }

    /**
     * 04 - 解除禁言
     * 
     * @param dto
     * @return
     */
    @ApiOperation(position = 4, value = "04 - 解除禁言", notes = "成员解除禁言")
    @RequestMapping(value = "/removeForbidden", method = RequestMethod.POST)
    @ApiOperationSupport(
        ignoreParameters = {"dto.pageSize", "dto.currentPage", "dto.id", "dto.userRole", "dto.isForbidden"})
    public ResponseEnvelope removeForbidden(@RequestBody WeGroupMemberDTO dto) {
        Integer groupId = dto.getGroupId();
        Integer uid = dto.getUid();
        return iWeGroupMemberService.removeForbidden(groupId, uid);
    }
}
