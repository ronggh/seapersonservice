package com.mukutech.seapersonservice.mapper;

import com.mukutech.seapersonservice.common.utils.response.ResponseEnvelope;
import com.mukutech.seapersonservice.entity.WeGroupMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LMYOU
 * @since 2020-08-08
 */
public interface WeGroupMemberMapper extends BaseMapper<WeGroupMember> {

    // 1. 判断用户是否为某个群的管理员
    @Select("select count(*) from we_group_member "+
            " where status = 1 and user_role ='1' and uid = #{uid} and group_id = #{groupId}")
    public Integer isManager(@Param("uid") Integer uid, @Param("groupId") Integer groupId);

}
