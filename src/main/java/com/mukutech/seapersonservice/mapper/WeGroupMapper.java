package com.mukutech.seapersonservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mukutech.seapersonservice.entity.WeGroup;
import com.mukutech.seapersonservice.pojo.vo.WeGroupLogoVO;
import com.mukutech.seapersonservice.pojo.vo.WeGroupSchoolMyJoinVO;
import com.mukutech.seapersonservice.pojo.vo.WeGroupSearchResultVO;
import com.mukutech.seapersonservice.pojo.vo.WeUserVO;

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
    List<WeGroupSearchResultVO> getMyJoinGroupList(Page<WeGroupSearchResultVO> page, @Param("uid") Integer uid);

    // 获取我管理的社群列表数据
    @Select("select g.group_id,g.group_name,g.group_desc,g.group_head_img,g.school_id,m.user_role from we_group g, we_group_member m where g.group_id=m.group_id and m.user_role = '1' and g.status=1 and m.status =1  and g.verify_flag='1' and m.uid =#{uid}")
    List<WeGroupSearchResultVO> getMyManagerGroupList(Page<WeGroupSearchResultVO> page, @Param("uid") Integer uid);

    /**
     * 获取群成员简要信息 userRole = 1,管理员 userRole = 2, 成员
     */
    @Select("select u.uid,u.nickname,u.introduce,u.head_img,u.verify_flag,m.user_role,m.is_forbidden "
        + " from we_user u, we_group_member m  " + " where u.uid = m.uid " + " and u.status = 1 "
        + "  and m.status = 1 " + " and m.user_role = #{userRole}" + " and m.group_id = #{groupId}")
    List<WeUserVO> getMemberBrief(@Param("groupId") Integer groupId, @Param("userRole") String userRole);

    /**
     * 处理关注关系 uid是否关注了uuid
     */
    @Select("select count(*) from we_user_relation where status = 1 and uid = #{uid} and uuid = #{uuid} ")
    Integer isFollowed(@Param("uid") Integer uid, @Param("uuid") Integer uuid);

    /**
     * 统计指定用户加入的非学校社群数量
     */
    @Select("select count(*) as group_num from we_group_member m,we_group g " + " where m.group_id = g.group_id  "
        + " and g.status = '1' and m.status = '1' " + " and g.verify_flag = '1' "
        + " and (g.school_id = 0 or g.school_id is null) " + " and m.uid = #{uid}")
    Integer statsJoinGroupNum(@Param("uid") Integer uid);

    /**
     * 获取指定用户加入的非学校社群的HeadImg集合
     * 
     * @param uid
     * @return
     */
    @Select("select g.group_id,g.group_head_img from we_group_member m,we_group g " + " where m.group_id = g.group_id "
        + " and g.status = '1' and m.status = '1' " + " and g.verify_flag = '1' "
        + " and (g.school_id = 0 or g.school_id is null) " + " and m.uid = #{uid}")
    List<WeGroupLogoVO> getJoinGroupHeadImg(@Param("uid") Integer uid);

    /**
     * 根据用户uid获取加入的学校社群列表
     * 
     * @param uid
     * @return
     */

    @Select("select s.school_id,s.school_cnname,school_enname " + " from we_school s,we_group g ,we_group_member m "
        + " where s.school_id= g.school_id " + " and m.group_id = g.group_id "
        + " and s.status = '1' and g.status = '1' and m.status = '1' " + "and g.verify_flag ='1' and m.uid = #{uid}")
    List<WeGroupSchoolMyJoinVO> getJoinSchoolGroup(@Param("uid") Integer uid);

    /**
     * 根据学校Id获取该学校下的社群数量
     * 
     * @param schoolId
     * @return
     */

    @Select("select count(*) as group_num " + " from we_group g, we_group_member m " + " where g.group_id = m.group_id "
        + " and m.status = '1' and g.status = '1' "
        + " and g.verify_flag = '1' and g.school_id = #{schoolId} and m.uid = #{uid}")
    Integer getSchoolGroupNum(@Param("uid") Integer uid, @Param("schoolId") Integer schoolId);

    /**
     * 根据学校Id获取该学校下的社群id和头像
     * 
     * @param schoolId
     * @return
     */

    @Select("select g.group_id,g.group_head_img " + " from we_school s,we_group g " + " where s.school_id= g.school_id "
        + " and s.status = '1' and g.status = '1' " + " and g.school_id = #{schoolId}")
    List<WeGroupLogoVO> getSchoolGroupHeadImg(@Param("schoolId") Integer schoolId);
}
