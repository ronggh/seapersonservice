package com.mukutech.seapersonservice.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.pojo.dto.WeGroupDTO;
import com.mukutech.seapersonservice.pojo.dto.WeGroupSearchDTO;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import com.mukutech.seapersonservice.service.IWeGroupService;
import org.springframework.stereotype.Controller;


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

    // 01-创建社群
    @ApiOperation(value = "01-创建社群", notes = "创建社群")
    @RequestMapping(value = "/addWeGroup", method = RequestMethod.POST)
    @ApiOperationSupport(ignoreParameters = {"dto.pageSize", "dto.currentPage", "dto.groupId"})
    public ResponseEnvelope addWeGroup(@RequestBody WeGroupDTO dto) {
        return iWeGroupService.addWeGroup(dto);
    }

    // 02-更新社群
    @ApiOperation(value = "02-更新社群", notes = "更新社群")
    @RequestMapping(value = "/updateWeGroup", method = RequestMethod.POST)
    @ApiOperationSupport(ignoreParameters = {"dto.pageSize", "dto.currentPage"})
    public ResponseEnvelope updateWeGroup(@RequestBody WeGroupDTO dto) {
        return iWeGroupService.updateWeGroup(dto);
    }

    // 03-删除社群
    @ApiOperation(value = "03-删除社群", notes = "删除社群")
    @RequestMapping(value = "/deleteWeGroup", method = RequestMethod.GET)
    public ResponseEnvelope deleteWeGroup(Integer groupId) {
        return iWeGroupService.deleteWeGroup(groupId);
    }


    // 04-搜索社群
    @ApiOperation(value = "04-搜索社群", notes = "根据社群名称或描述，搜索结果列表分页显示")
    @RequestMapping(value = "/searchGroup", method = RequestMethod.POST)
    public ResponseEnvelope searchWeGroupListPage(@RequestBody WeGroupSearchDTO dto) {
        return iWeGroupService.searchGroup(dto);
    }

    // 05-我加入的社群列表
    @ApiOperation(value = "05-我加入的社群列表", notes = "我加入的社群列表，分页显示")
    @RequestMapping(value = "/getMyJoinGroupList", method = RequestMethod.POST)
    @ApiOperationSupport(ignoreParameters = {"dto.query"})
    public ResponseEnvelope getMyJoinGroupList(@RequestBody WeGroupSearchDTO dto) {
        return iWeGroupService.getMyJoinGroupList(dto);
    }

    // 06-我管理的社群列表
    @ApiOperation(value = "06-我管理的社群列表", notes = "我管理的社群列表，分页显示")
    @RequestMapping(value = "/getMyManagerGroupList", method = RequestMethod.POST)
    @ApiOperationSupport(ignoreParameters = {"dto.query"})
    public ResponseEnvelope getMyManagerGroupList(@RequestBody WeGroupSearchDTO dto) {
        return iWeGroupService.getMyManagerGroupList(dto);
    }

    // 07-我加入的社群详情
    @ApiOperation(value = "07-我加入的社群详情", notes = "查询我加入的指定的某一个社群及成员信息")
    @RequestMapping(value = "/myJoinGroupDetail", method = RequestMethod.GET)
    public ResponseEnvelope myJoinGroupDetail(Integer groupId, Integer uid) {

        return iWeGroupService.myJoinGroupDetail(groupId, uid);
    }

}
