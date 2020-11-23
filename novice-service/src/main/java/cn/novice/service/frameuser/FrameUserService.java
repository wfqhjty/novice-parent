package cn.novice.service.frameuser;

import cn.novice.dao.FrameUserMapper;
import cn.novice.entity.FrameUser;
import cn.novice.service.redis.RedisDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class FrameUserService {

    @Resource
    private FrameUserMapper frameUserMapper;

    public FrameUser selectByNamePasswd(String username, String passwd) {
        return frameUserMapper.selectByNamePasswd(username, passwd);
    }

    public FrameUser selectByPrimaryKey(Integer uid) {
        return frameUserMapper.selectByPrimaryKey(uid);
    }

    public FrameUser selectByUsername(String username) {
        return frameUserMapper.selectByUsername(username);
    }

    public void insert(FrameUser frameUser) {
        frameUserMapper.insert(frameUser);
    }

    public void deleteByPrimaryKey(Integer uid) {
        frameUserMapper.deleteByPrimaryKey(uid);
    }
}
