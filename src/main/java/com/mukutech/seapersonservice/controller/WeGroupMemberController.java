package com.mukutech.seapersonservice.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.pojo.dto.WeGroupMemberDTO;
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
    @ApiOperation(value = "01 - 加入社群", notes = "用户加入社群")
    @RequestMapping(value = "/joinGroup", method = RequestMethod.GET)
    public ResponseEnvelope joinGroup(@Param("uid") Integer uid, @Param("groupId") Integer groupId) {
        return iWeGroupMemberService.joinGroup(uid, groupId);
    }

    @ApiOperation(value = "02 - 退出社群", notes = "社群成员退出")
    @RequestMapping(value = "/quitGroup", method = RequestMethod.GET)
    public ResponseEnvelope quitGroup(Integer uid, Integer groupId) {
        return iWeGroupMemberService.quitGroup(uid,groupId);
    }

}
