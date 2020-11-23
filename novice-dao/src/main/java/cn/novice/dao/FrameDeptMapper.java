package cn.novice.dao;

import cn.novice.entity.FrameDept;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameDeptMapper {
    int deleteByPrimaryKey(Integer deptid);

    int insert(FrameDept record);

    int insertSelective(FrameDept record);

    FrameDept selectByPrimaryKey(Integer deptid);

    int updateByPrimaryKeySelective(FrameDept record);

    int updateByPrimaryKey(FrameDept record);
}