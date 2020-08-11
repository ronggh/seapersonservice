package com.mukutech.seapersonservice.controller;


import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import com.mukutech.seapersonservice.service.IWeBlackUserService;
import com.mukutech.seapersonservice.pojo.entity.dto.WeBlackUserDTO;
import org.springframework.stereotype.Controller;




/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
@Slf4j
@Api(tags = {"API"})
    @Controller
@RequestMapping("/seapersonservice/weBlackUser")
@ResponseBody
    public class WeBlackUserController {

@Autowired
private IWeBlackUserService  iWeBlackUserService;


@ApiOperation(value = "列表分页查询", notes = "API")
@RequestMapping(value = "/searchWeBlackUserListPage", method = RequestMethod.POST)
public ResponseEnvelope searchWeBlackUserListPage(@RequestBody WeBlackUserDTO  dto){
        return iWeBlackUserService.searchWeBlackUserListPage(dto);
        }
@ApiOperation(value = "详细查询", notes = "API")
@RequestMapping(value = "/searchWeBlackUserOne", method = RequestMethod.GET)
public ResponseEnvelope searchWeBlackUserOne(Integer id){
        return iWeBlackUserService.searchWeBlackUserOne(id);
        }
@ApiOperation(value = "添加", notes = "API")
@RequestMapping(value = "/addWeBlackUser", method = RequestMethod.POST)
public ResponseEnvelope addWeBlackUser(@RequestBody com.mukutech.seapersonservice.pojo.entity.dto.WeBlackUserDTO dto){
        return iWeBlackUserService.addWeBlackUser(dto);
        }
@ApiOperation(value = "更新", notes = "API")
@RequestMapping(value = "/updateWeBlackUser", method = RequestMethod.POST)
public ResponseEnvelope updateWeBlackUser(@RequestBody WeBlackUserDTO dto){
        return iWeBlackUserService.updateWeBlackUser(dto);
        }
@ApiOperation(value = "删除", notes = "API")
@RequestMapping(value = "/deleteWeBlackUser", method = RequestMethod.GET)
public ResponseEnvelope deleteWeBlackUser(Integer id){
        return iWeBlackUserService.deleteWeBlackUser(id);
        }

        }
