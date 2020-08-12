package com.mukutech.seapersonservice.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mukutech.seapersonservice.entity.WeBlackUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mukutech.seapersonservice.pojo.vo.WeBlackUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-07
 */
public interface WeBlackUserMapper extends BaseMapper<WeBlackUser> {
    // 根据用户uid获取黑名单信息，分页
    @Select("select b.uid,b.black_uid,u.nickname,u.real_name,u.head_img "
            + " from we_black_user b,we_user u "
            + " where b.black_uid = u.uid "
            + " and b.status='1' and u.status='1' "
            + " and b.uid=#{uid}")
    List<WeBlackUserVO> getBlackListByUid(Page<WeBlackUserVO> page, @Param("uid") Integer uid);

    // 根据用户uid,模糊搜索被拉黑的黑名单中昵称和真名匹配的信息，分页
    @Select("select b.uid,b.black_uid,u.nickname,u.real_name,u.head_img "
            + " from we_black_user b,we_user u "
            + " where b.black_uid = u.uid "
            + " and b.status='1' and u.status='1' "
            + " and b.uid=#{uid} "
            + " and (u.nickname like #{query} or u.real_name like #{query} )")
    List<WeBlackUserVO> searchBlackList(Page<WeBlackUserVO> page, @Param("uid") Integer uid,@Param("query") String query);


}
