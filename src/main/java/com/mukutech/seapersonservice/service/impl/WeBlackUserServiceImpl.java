package com.mukutech.seapersonservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukutech.seapersonservice.common.utils.BeanCopyUtil;
import com.mukutech.seapersonservice.common.utils.ResultVOUtil;
import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.entity.WeBlackUser;
import com.mukutech.seapersonservice.mapper.WeBlackUserMapper;
import com.mukutech.seapersonservice.service.IWeBlackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import com.mukutech.seapersonservice.pojo.entity.dto.WeBlackUserDTO;
/**
 * <p>
 *  服务实现类
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

        @Override
        public ResponseEnvelope searchWeBlackUserListPage(WeBlackUserDTO dto){
        Page<WeBlackUser> page=new Page<WeBlackUser>();
        page.setSize(dto.getPageSize());
        page.setCurrent(dto.getCurrentPage());
        QueryWrapper<WeBlackUser> queryWrapper=new QueryWrapper<WeBlackUser>();
    WeBlackUser entity=new WeBlackUser();
        BeanCopyUtil.copyPropertiesIgnoreNull(dto,entity);
        queryWrapper.setEntity(entity);
        return ResultVOUtil.returnSuccess(weBlackUserMapper.selectPage(page,queryWrapper));
        }
        @Override
        public ResponseEnvelope searchWeBlackUserOne(Integer id){

        return ResultVOUtil.returnSuccess(this.selectOne(id));
        }
        @Override
        public ResponseEnvelope addWeBlackUser(WeBlackUserDTO dto){
    WeBlackUser entity=new WeBlackUser();
        BeanCopyUtil.copyPropertiesIgnoreNull(dto,entity);
    weBlackUserMapper.insert(entity);
        return ResultVOUtil.returnSuccess();
        }
        @Override
        public ResponseEnvelope updateWeBlackUser(WeBlackUserDTO dto){
    WeBlackUser entity=this.selectOne(dto.getId());
        BeanCopyUtil.copyPropertiesIgnoreNull(dto,entity);
    weBlackUserMapper.updateById(entity);
        return ResultVOUtil.returnSuccess();
        }
        @Override
        public ResponseEnvelope deleteWeBlackUser(Integer id){
    weBlackUserMapper.deleteById(id);
        return ResultVOUtil.returnSuccess();
        }


        public WeBlackUser selectOne(Integer id){
    WeBlackUser entity=new WeBlackUser();
        entity.setId(id);
        QueryWrapper<WeBlackUser>queryWrapper=new QueryWrapper<>();
        queryWrapper.setEntity(entity);
        return weBlackUserMapper.selectOne(queryWrapper);
        }

        }
