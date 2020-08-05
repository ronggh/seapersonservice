package com.mukutech.seapersonservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.pojo.dto.*;
import com.mukutech.seapersonservice.pojo.entity.TestDemo;

import java.util.List;


/**
 * <p>
 * 用户基础属性表 服务类
 * </p>
 *
 * @author LMYOU
 * @since 2020-07-22
 */
public interface ITestDemoService extends IService<TestDemo> {
    ResponseEnvelope searchTestDemoListPage(TestDemoDTO DTO);

    public ResponseEnvelope searchTestDemoOne(Long id);

    public ResponseEnvelope addTestDemo(TestDemoDTO DTO);

    public ResponseEnvelope updateTestDemo(TestDemoDTO DTO);

    public ResponseEnvelope deleteTestDemo(Long id);

    ResponseEnvelope addNotes(NotesDTO dto);

    ResponseEnvelope getTopicList(String topicName);

    ResponseEnvelope getShopCarList(ShopCarDTO dto);

    ResponseEnvelope updateNumByGid(ShopCarDTO dto);

    ResponseEnvelope delGoodByIds(List<Long> goodIds);

    ResponseEnvelope calPriceByGids(List<ShopCarDTO> dto);

    ResponseEnvelope getMyOrderList(Long userId);

    ResponseEnvelope addMyFeedBack(FeedBackDTO feedBackDTO);

    ResponseEnvelope rechargeMyAccount(RechargeDTO rechargeDTO);

    ResponseEnvelope getMyBillList(Long userId, Integer month);

    ResponseEnvelope setMyAccountSafe(AccountSafeDTO accountSafeDTO);

    ResponseEnvelope loginOut(Long userId);

    ResponseEnvelope hideMyTrace(Long userId);

    ResponseEnvelope lockLoginInfo(AccountSafeDTO accountSafeDTO);

    ResponseEnvelope getMyBlackList(Long userId);
}
