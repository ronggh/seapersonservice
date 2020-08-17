package com.mukutech.seapersonservice.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mukutech.seapersonservice.entity.WeGroupMember;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-08
 */
public interface WeGroupMemberMapper extends BaseMapper<WeGroupMember> {

    /**
     * 1. 判断用户是否为某个群的管理员
     * 
     * @param uid
     * @param groupId
     * @return
     */
    @Select("select count(*) from we_group_member "
        + " where status = 1 and user_role ='1' and uid = #{uid} and group_id = #{groupId}")
    Integer isManager(@Param("uid") Integer uid, @Param("groupId") Integer groupId);

}
