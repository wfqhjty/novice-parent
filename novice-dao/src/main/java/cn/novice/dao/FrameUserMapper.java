package cn.novice.dao;

import cn.novice.entity.FrameUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameUserMapper {

    int insert(FrameUser record);

    int insertSelective(FrameUser record);

    FrameUser selectByPrimaryKey(Integer userid);

    FrameUser selectByUsername(String username);

    FrameUser selectByNamePasswd(@Param("username") String username, @Param("passwd") String passwd);

    int updateByPrimaryKeySelective(FrameUser record);

    int updateByPrimaryKey(FrameUser record);

    int deleteByPrimaryKey(Integer userid);
}