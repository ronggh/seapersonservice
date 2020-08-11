package com.mukutech.seapersonservice.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mukutech.seapersonservice.entity.WeGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.mukutech.seapersonservice.pojo.vo.WeGroupSearchResultVO;
import com.mukutech.seapersonservice.pojo.vo.WeUserVO;
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
public interface WeGroupMapper extends BaseMapper<WeGroup> {

    // 获取我加入的社群列表数据
    @Select("select g.group_id,g.group_name,g.group_desc,g.group_head_img,g.school_id,m.user_role from we_group g, we_group_member m where g.group_id=m.group_id and m.user_role = '2' and g.status=1 and m.status =1  and g.verify_flag='1' and m.uid =#{uid}")
    public List<WeGroupSearchResultVO> getMyJoinGroupList(Page<WeGroupSearchResultVO>page,@Param("uid") Integer uid);

    // 获取我管理的社群列表数据
    @Select("select g.group_id,g.group_name,g.group_desc,g.group_head_img,g.school_id,m.user_role from we_group g, we_group_member m where g.group_id=m.group_id and m.user_role = '1' and g.status=1 and m.status =1  and g.verify_flag='1' and m.uid =#{uid}")
    public List<WeGroupSearchResultVO> getMyManagerGroupList(Page<WeGroupSearchResultVO>page,@Param("uid") Integer uid);

    /**
     * 获取群成员简要信息
     * userRole = 1,管理员
     * userRole = 2, 成员
      */
    @Select("select u.uid,u.nickname,u.introduce,u.head_img,u.verify_flag,m.user_role "
            +" from we_user u, we_group_member m  "
            +" where u.uid = m.uid "
            + " and u.status = 1 "
            +"  and m.status = 1 "
            + " and m.user_role = #{userRole}"
            + " and m.group_id = #{groupId}" )
    public List<WeUserVO> getMemberBrief(@Param("groupId") Integer groupId,@Param("userRole") String userRole);

    /**
     * 处理关注关系
     * uid是否关注了uuid
      */
    @Select("select count(*) from we_user_relation where status = 1 and uid = #{uid} and uuid = #{uuid} ")
    public Integer isFollowed(@Param("uid") Integer uid,@Param("uuid") Integer uuid);
}
