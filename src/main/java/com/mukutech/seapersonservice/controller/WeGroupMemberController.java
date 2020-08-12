package com.mukutech.seapersonservice.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.pojo.dto.WeGroupMemberDTO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import com.mukutech.seapersonservice.service.IWeGroupMemberService;

import org.springframework.stereotype.Controller;


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

    // 01- 用户加入社群
    @ApiOperation(position = 1, value = "01 - 加入社群", notes = "用户加入社群")
    @RequestMapping(value = "/joinGroup", method = RequestMethod.GET)
    public ResponseEnvelope joinGroup(@Param("uid") Integer uid, @Param("groupId") Integer groupId) {
        return iWeGroupMemberService.joinGroup(uid, groupId);
    }

    @ApiOperation(position = 2, value = "02 - 退出社群", notes = "社群成员退出")
    @RequestMapping(value = "/quitGroup", method = RequestMethod.GET)
    public ResponseEnvelope quitGroup(Integer uid, Integer groupId) {
        return iWeGroupMemberService.quitGroup(uid, groupId);
    }

    @ApiOperation(position = 3, value = "03 - 成员禁言/解禁", notes = "成员禁言/解禁，isForbidden -- 0：解禁 ，1：禁言")
    @RequestMapping(value = "/quitGroup", method = RequestMethod.POST)
    @ApiOperationSupport(ignoreParameters = {"dto.pageSize", "dto.currentPage", "dto.id", "dto.userRole"})
    public ResponseEnvelope forbiddenOrNot(@RequestBody WeGroupMemberDTO dto) {
        Integer groupId = dto.getGroupId();
        Integer uid = dto.getUid();
        String isForbidden = dto.getIsForbidden();
        return iWeGroupMemberService.forbiddenOrNot(groupId, uid, isForbidden);
    }

}
