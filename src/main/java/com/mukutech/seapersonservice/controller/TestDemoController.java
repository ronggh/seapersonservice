package com.mukutech.seapersonservice.controller;


import com.mukutech.seapersonservice.common.utils.ResultVOUtil;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.pojo.dto.*;
import com.mukutech.seapersonservice.service.ITestDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 用户基础属性表 前端控制器
 * </p>
 *
 * @author LMYOU
 * @since 2020-07-22
 */
@Slf4j
@Api(tags = {"用户基础属性表API"})
@Controller
@RequestMapping("/seapersonservice/testDemo")
@ResponseBody
@Data
public class TestDemoController {

    @Autowired
    private ITestDemoService iTestDemoService;

    @Value("${file.fileupload}")
    private String fileupload;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;


//    @ApiOperation(value = "用户基础属性表列表分页查询", notes = "用户基础属性表API")
//    @RequestMapping(value = "/searchTestDemoListPage", method = RequestMethod.POST)
//    public ResponseEnvelope searchTestDemoListPage(@RequestBody TestDemoDTO dto) {
//        return iTestDemoService.searchTestDemoListPage(dto);
//    }
//
//    @ApiOperation(value = "用户基础属性表详细查询", notes = "用户基础属性表API")
//    @RequestMapping(value = "/searchTestDemoOne", method = RequestMethod.GET)
//    public ResponseEnvelope searchTestDemoOne(Long id) {
//        return iTestDemoService.searchTestDemoOne(id);
//    }
//
//    @ApiOperation(value = "用户基础属性表添加", notes = "用户基础属性表API")
//    @RequestMapping(value = "/addTestDemo", method = RequestMethod.POST)
//    public ResponseEnvelope addTestDemo(@RequestBody TestDemoDTO dto) {
//        return iTestDemoService.addTestDemo(dto);
//    }
//
//    @ApiOperation(value = "用户基础属性表更新", notes = "用户基础属性表API")
//    @RequestMapping(value = "/updateTestDemo", method = RequestMethod.POST)
//    public ResponseEnvelope updateTestDemo(@RequestBody TestDemoDTO dto) {
//        return iTestDemoService.updateTestDemo(dto);
//    }
//
//    @ApiOperation(value = "用户基础属性表删除", notes = "用户基础属性表API")
//    @RequestMapping(value = "/deleteTestDemo", method = RequestMethod.GET)
//    public ResponseEnvelope deleteTestDemo(Long id) {
//        return iTestDemoService.deleteTestDemo(id);
//    }


    /***************用户发布笔记接口********************/
    @ApiOperation(value = "发布笔记接口", notes = "用户发布笔记API")
    @RequestMapping(value = "/addNotes", method = RequestMethod.POST)
    public ResponseEnvelope addNotes(@RequestBody NotesDTO dto) {
        return iTestDemoService.addNotes(dto);
    }

    @ApiOperation(value = "文件上传接口", notes = "用户发布笔记API")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEnvelope upload(@RequestParam("photos") List<MultipartFile> photos, HttpServletRequest request) {// 定义上传文件存放的路径
        String path = fileupload + "uploadPhotos/";
        System.out.println(path);
        // 定义文件在上传路径中的文件夹名称
        File folder = new File(path);
        // 检测folder是否是文件夹，不是就创建
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        List<String> photoArr = new ArrayList<>();
        for (MultipartFile photo : photos) {
            try {
                // 获取文件的原始名称
                String photoName = photo.getOriginalFilename();
                String suffix = photoName.substring(photoName.lastIndexOf(".") + 1);
                // 对文件进行重命名
                String imgName = "pic_" + System.currentTimeMillis() + "." + suffix;
                // 文件保存操作
                photo.transferTo(new File(folder, imgName));
                // 访问的url
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
                String photoPath = basePath + staticAccessPath.substring(0, staticAccessPath.lastIndexOf("/") + 1) + "uploadPhotos/" + imgName;
                //System.out.println(basePath);
                //System.out.println(photoPath);
                photoArr.add(photoPath);
            } catch (IOException e) {
                return ResultVOUtil.returnFail();
            }
        }
        return ResultVOUtil.returnSuccess(photoArr);
    }


    @ApiOperation(value = "获取话题列表接口，支持模糊查询", notes = "用户发布笔记API")
    @RequestMapping(value = "/getTopicList", method = RequestMethod.POST)
    public ResponseEnvelope getTopicList(String topicName) {
        return iTestDemoService.getTopicList(topicName);
    }

    /***************用户发布笔记接口********************/

    /***************个人中心-购物车-接口********************/

    @ApiOperation(value = "获取我的购物车列表", notes = "个人中心-购物车API")
    @RequestMapping(value = "/getShopCarList", method = RequestMethod.POST)
    public ResponseEnvelope getShopCarList(@RequestBody ShopCarDTO dto) {
        return iTestDemoService.getShopCarList(dto);
    }


    @ApiOperation(value = "根据商品ID来修改购物车中商品购买数量", notes = "个人中心-购物车API")
    @RequestMapping(value = "/updateNumByGid", method = RequestMethod.POST)
    public ResponseEnvelope updateNumByGid(@RequestBody ShopCarDTO dto) {
        return iTestDemoService.updateNumByGid(dto);
    }


    @ApiOperation(value = "根据商品ID删除购物车中的商品", notes = "个人中心-购物车API")
    @RequestMapping(value = "/delGoodByIds", method = RequestMethod.POST)
    public ResponseEnvelope delGoodByIds(@RequestParam("goodIds") List<Long> goodIds) {
        return iTestDemoService.delGoodByIds(goodIds);
    }

    @ApiOperation(value = "购物车中的商品批量结算并生成对应订单", notes = "个人中心-购物车API")
    @RequestMapping(value = "/calPriceByGids", method = RequestMethod.POST)
    public ResponseEnvelope calPriceByGids(@RequestBody List<ShopCarDTO> dto) {
        return iTestDemoService.calPriceByGids(dto);
    }
    /***************个人中心-购物车-接口********************/

    /***************个人中心-我的订单-接口********************/
    @ApiOperation(value = "获取我的订单列表", notes = "个人中心-我的订单API")
    @RequestMapping(value = "/getMyOrderList", method = RequestMethod.POST)
    public ResponseEnvelope getMyOrderList(@RequestParam("userId") Long userId) {
        return iTestDemoService.getMyOrderList(userId);
    }

    /***************个人中心-我的订单-接口********************/


    /***************个人中心-帮助与反馈-接口********************/
    @ApiOperation(value = "提交我的问题反馈", notes = "个人中心-我的订单API")
    @RequestMapping(value = "/addMyFeedBack", method = RequestMethod.POST)
    public ResponseEnvelope addMyFeedBack(@RequestBody FeedBackDTO feedBackDTO) {
        return iTestDemoService.addMyFeedBack(feedBackDTO);
    }
    /***************个人中心-帮助与反馈-接口********************/


    /***************个人中心-钱包充值提现-接口********************/
    @ApiOperation(value = "充值or提现接口", notes = "个人中心-钱包充值提现API")
    @RequestMapping(value = "/rechargeMyAccount", method = RequestMethod.POST)
    public ResponseEnvelope rechargeMyAccount(@RequestBody RechargeDTO rechargeDTO) {
        return iTestDemoService.rechargeMyAccount(rechargeDTO);
    }

    /***************个人中心-钱包充值提现-接口********************/


    /***************个人中心-钱包账单-接口********************/
    @ApiOperation(value = "获取我的钱包账单列表", notes = "个人中心-钱包账单API")
    @RequestMapping(value = "/getMyBillList", method = RequestMethod.POST)
    public ResponseEnvelope getMyBillList(@RequestParam("userId") Long userId, @RequestParam("month") Integer month) {
        return iTestDemoService.getMyBillList(userId, month);
    }
    /***************个人中心-钱包账单-接口********************/


    /***************个人中心-设置-接口********************/
    @ApiOperation(value = "设置我的账号安全，设置登录密码微信微博QQ账号", notes = "个人中心-设置API")
    @RequestMapping(value = "/setMyAccountSafe", method = RequestMethod.POST)
    public ResponseEnvelope setMyAccountSafe(@RequestBody AccountSafeDTO accountSafeDTO) {
        return iTestDemoService.setMyAccountSafe(accountSafeDTO);
    }

    @ApiOperation(value = "注销我的账号", notes = "个人中心-设置API")
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public ResponseEnvelope loginOut(@RequestParam("userId") Long userId) {
        return iTestDemoService.loginOut(userId);
    }

    @ApiOperation(value = "设置隐藏我的笔记", notes = "个人中心-设置API")
    @RequestMapping(value = "/hideMyTrace", method = RequestMethod.POST)
    public ResponseEnvelope hideMyTrace(@RequestParam("userId") Long userId) {
        return iTestDemoService.hideMyTrace(userId);
    }

    @ApiOperation(value = "记住登录信息", notes = "个人中心-设置API")
    @RequestMapping(value = "/lockLoginInfo", method = RequestMethod.POST)
    public ResponseEnvelope lockLoginInfo(@RequestBody AccountSafeDTO accountSafeDTO) {
        return iTestDemoService.lockLoginInfo(accountSafeDTO);
    }

    @ApiOperation(value = "获取我的黑名单列表", notes = "个人中心-设置API")
    @RequestMapping(value = "/getMyBlackList", method = RequestMethod.POST)
    public ResponseEnvelope getMyBlackList(@RequestParam("userId") Long userId) {
        return iTestDemoService.getMyBlackList(userId);
    }
    /***************个人中心-设置-接口********************/
}
