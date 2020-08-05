package com.mukutech.seapersonservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukutech.seapersonservice.common.utils.BeanCopyUtil;
import com.mukutech.seapersonservice.common.utils.ResultVOUtil;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.mapper.TestDemoMapper;
import com.mukutech.seapersonservice.pojo.dto.*;
import com.mukutech.seapersonservice.pojo.entity.TestDemo;
import com.mukutech.seapersonservice.service.ITestDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户基础属性表 服务实现类
 * </p>
 *
 * @author LMYOU
 * @since 2020-07-22
 */
@Slf4j
@Transactional
@Service
public class TestDemoServiceImpl extends ServiceImpl<TestDemoMapper, TestDemo> implements ITestDemoService {


    @Autowired
    private TestDemoMapper testDemoMapper;

    @Override
    public ResponseEnvelope searchTestDemoListPage(TestDemoDTO dto) {
        Page<TestDemo> page = new Page<TestDemo>();
        page.setSize(dto.getPageSize());
        page.setCurrent(dto.getCurrentPage());
        QueryWrapper<TestDemo> queryWrapper = new QueryWrapper<TestDemo>();
        TestDemo entity = new TestDemo();
        BeanCopyUtil.copyPropertiesIgnoreNull(dto, entity);
        queryWrapper.setEntity(entity);
        return ResultVOUtil.returnSuccess(testDemoMapper.selectPage(page, queryWrapper));
    }

    @Override
    public ResponseEnvelope searchTestDemoOne(Long id) {
        return ResultVOUtil.returnSuccess(this.selectOne(id));
    }

    @Override
    public ResponseEnvelope addTestDemo(TestDemoDTO dto) {
        TestDemo entity = new TestDemo();
        BeanCopyUtil.copyPropertiesIgnoreNull(dto, entity);
        testDemoMapper.insert(entity);
        return ResultVOUtil.returnSuccess();
    }

    @Override
    public ResponseEnvelope updateTestDemo(TestDemoDTO dto) {
        TestDemo entity = this.selectOne(dto.getId());
        BeanCopyUtil.copyPropertiesIgnoreNull(dto, entity);
        testDemoMapper.updateById(entity);
        return ResultVOUtil.returnSuccess();
    }

    @Override
    public ResponseEnvelope deleteTestDemo(Long id) {
        testDemoMapper.deleteById(id);
        return ResultVOUtil.returnSuccess();
    }

    @Override
    public ResponseEnvelope addNotes(NotesDTO dto) {
        return ResultVOUtil.returnSuccess();
    }

    @Override
    public ResponseEnvelope getTopicList(String topicName) {
        return ResultVOUtil.returnSuccess();
    }

    @Override
    public ResponseEnvelope getShopCarList(ShopCarDTO dto) {
        return ResultVOUtil.returnSuccess();
    }

    @Override
    public ResponseEnvelope updateNumByGid(ShopCarDTO dto) {
        return null;
    }

    @Override
    public ResponseEnvelope delGoodByIds(List<Long> goodIds) {
        return null;
    }

    @Override
    public ResponseEnvelope calPriceByGids(List<ShopCarDTO> dto) {
        return null;
    }

    @Override
    public ResponseEnvelope getMyOrderList(Long userId) {
        return null;
    }

    @Override
    public ResponseEnvelope addMyFeedBack(FeedBackDTO feedBackDTO) {
        return null;
    }

    @Override
    public ResponseEnvelope rechargeMyAccount(RechargeDTO rechargeDTO) {
        return null;
    }

    @Override
    public ResponseEnvelope getMyBillList(Long userId, Integer month) {
        return null;
    }

    @Override
    public ResponseEnvelope setMyAccountSafe(AccountSafeDTO accountSafeDTO) {
        return null;
    }

    @Override
    public ResponseEnvelope loginOut(Long userId) {
        return null;
    }

    @Override
    public ResponseEnvelope hideMyTrace(Long userId) {
        return null;
    }

    @Override
    public ResponseEnvelope lockLoginInfo(AccountSafeDTO accountSafeDTO) {
        return null;
    }

    @Override
    public ResponseEnvelope getMyBlackList(Long userId) {
        return null;
    }


    public TestDemo selectOne(Long id) {
        TestDemo entity = new TestDemo();
        entity.setId(id);
        QueryWrapper<TestDemo> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(entity);
        return testDemoMapper.selectOne(queryWrapper);
    }

}
