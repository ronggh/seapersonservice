package com.mukutech.seapersonservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.pojo.dto.WeBlackUserDTO;
import com.mukutech.seapersonservice.pojo.vo.PageVO;
import com.mukutech.seapersonservice.pojo.vo.WeBlackUserVO;
import com.mukutech.seapersonservice.service.IWeBlackUserService;

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
@Api(tags = {"黑名单管理"})
@Controller
@RequestMapping("/seapersonservice/weBlackUser")
@ResponseBody
public class WeBlackUserController {

    @Autowired
    private IWeBlackUserService iWeBlackUserService;

    /**
     * 01-加入黑名单
     * 
     * @param dto
     * @return
     * 
     *         position 用来在生成的文档中进行排序
     */

    @ApiOperation(position = 1, value = "01-加入黑名单", notes = "将某个用户加入到黑名单中")
    @RequestMapping(value = "/addBlack", method = RequestMethod.POST)
    @ApiOperationSupport(includeParameters = {"dto.uid", "dto.blackUid"})
    public ResponseEnvelope addBlack(@RequestBody WeBlackUserDTO dto) {
        Integer uid = dto.getUid();
        Integer blackUid = dto.getBlackUid();
        return iWeBlackUserService.addBlack(uid, blackUid);
    }

    /**
     * 02-从黑名单中移除
     * 
     * @param dto
     * @return
     */
    @ApiOperation(position = 2, value = "02-从黑名单中移除", notes = "将某个用户从黑名单中移除")
    @RequestMapping(value = "/removeBlack", method = RequestMethod.POST)
    @ApiOperationSupport(includeParameters = {"dto.uid", "dto.blackUid"})
    public ResponseEnvelope removeBlack(@RequestBody WeBlackUserDTO dto) {
        Integer uid = dto.getUid();
        Integer blackUid = dto.getBlackUid();
        return iWeBlackUserService.removeBlack(uid, blackUid);
    }

    /**
     * 03-获取某用户的全部黑名单
     * 
     * @param dto
     * @return
     */
    @ApiOperation(position = 3, value = "03-获取某用户的全部黑名单", notes = "全部黑名单，分页显示")
    @RequestMapping(value = "/getAllBlackListPage", method = RequestMethod.POST)
    @ApiOperationSupport(ignoreParameters = {"dto.blackUid", "dto.query"})
    public ResponseEnvelope<PageVO<WeBlackUserVO>> getAllBlackListPage(@RequestBody WeBlackUserDTO dto) {
        return iWeBlackUserService.getAllBlackListPage(dto);
    }

    /**
     * 04-搜索某用户的黑名单
     * 
     * @param dto
     * @return
     */
    @ApiOperation(position = 4, value = "04-搜索某用户的黑名单", notes = "按被拉黑人的昵称和真名模糊匹配，分页显示")
    @RequestMapping(value = "/searchBlackListPage", method = RequestMethod.POST)
    @ApiOperationSupport(ignoreParameters = {"dto.blackUid"})
    public ResponseEnvelope<PageVO<WeBlackUserVO>> searchBlackListPage(@RequestBody WeBlackUserDTO dto) {
        return iWeBlackUserService.searchBlackListPage(dto);
    }

}
