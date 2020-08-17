package com.mukutech.seapersonservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukutech.seapersonservice.common.utils.ResultVOUtil;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.entity.WeBlackUser;
import com.mukutech.seapersonservice.mapper.WeBlackUserMapper;
import com.mukutech.seapersonservice.pojo.dto.WeBlackUserDTO;
import com.mukutech.seapersonservice.pojo.vo.PageVO;
import com.mukutech.seapersonservice.pojo.vo.WeBlackUserVO;
import com.mukutech.seapersonservice.service.IWeBlackUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
@Slf4j
@Transactional
@Service
public class WeBlackUserServiceImpl extends ServiceImpl<WeBlackUserMapper, WeBlackUser> implements IWeBlackUserService {

    @Autowired
    private WeBlackUserMapper weBlackUserMapper;

    /**
     * 01 - 加入黑名单
     * 
     * @param uid
     * @param blackUid
     * @return
     */
    @Override
    public ResponseEnvelope addBlack(Integer uid, Integer blackUid) {
        // 1. 初始化实体类
        WeBlackUser weBlackUser = new WeBlackUser();
        weBlackUser.setUid(uid);
        weBlackUser.setBlackUid(blackUid);
        // 1：代表有效数据
        weBlackUser.setStatus("1");
        // 2.下面两句功能一样
        Integer result = weBlackUserMapper.insert(weBlackUser);
        // weBlackUser.insert();
        // 3. 返回数据
        return result > 0 ? ResultVOUtil.returnSuccess() : ResultVOUtil.returnFail();
    }

    /**
     * 02 - 从黑名单中移除
     * 
     * @param uid
     * @param blackUid
     * @return
     */
    @Override
    public ResponseEnvelope removeBlack(Integer uid, Integer blackUid) {

        Map<String, Object> map = new HashMap<>(16);
        map.put("uid", uid);
        map.put("black_uid", blackUid);

        Integer result = weBlackUserMapper.deleteByMap(map);
        return result > 0 ? ResultVOUtil.returnSuccess() : ResultVOUtil.returnFail();
    }

    /**
     * 03 -根据用户uid获取黑名单，分页显示
     * 
     * @param dto
     * @return
     */
    @Override
    public ResponseEnvelope getAllBlackListPage(WeBlackUserDTO dto) {
        // 1. 定义分页对象并赋值
        Page<WeBlackUserVO> page = new Page<>();
        page.setSize(dto.getPageSize());
        page.setCurrent(dto.getCurrentPage());

        // 2. 执行查询
        List<WeBlackUserVO> list = weBlackUserMapper.getBlackListByUid(page, dto.getUid());
        page.setRecords(list);
        // 3.返回结果,封装成自己的分页数据格式
        PageVO<WeBlackUserVO> result = new PageVO<>();
        result.setCurrent(page.getCurrent());
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());
        result.setSize(page.getSize());
        result.setRecords(list);
        return ResultVOUtil.returnSuccess(result);
    }

    /**
     * 04 - 搜索指定用户的黑名单，按被拉黑人的昵称和真名模糊匹配
     * 
     * @param dto
     * @return
     */
    @Override
    public ResponseEnvelope searchBlackListPage(WeBlackUserDTO dto) {
        // 1. 定义分页对象并赋值
        Page<WeBlackUserVO> page = new Page<>();
        page.setSize(dto.getPageSize());
        page.setCurrent(dto.getCurrentPage());
        // 2. 执行查询
        String query = "%" + dto.getQuery() + "%";
        List<WeBlackUserVO> list = weBlackUserMapper.searchBlackList(page, dto.getUid(), query);
        page.setRecords(list);
        // 3.返回结果
        PageVO<WeBlackUserVO> result = new PageVO<>();
        result.setCurrent(page.getCurrent());
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());
        result.setSize(page.getSize());
        result.setRecords(list);
        return ResultVOUtil.returnSuccess(result);
    }

}
