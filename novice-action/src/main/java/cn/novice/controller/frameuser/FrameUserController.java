package cn.novice.controller.frameuser;

import cn.novice.entity.FrameUser;
import cn.novice.service.frameuser.FrameUserService;
import cn.novice.service.redis.RedisDao;
import cn.novice.utils.annotation.MyJwtAnnotation;
import cn.novice.utils.jwt.JwtUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/frameUserController")
public class FrameUserController {

    private static final Logger logger = LoggerFactory.getLogger(FrameUserController.class);

    @Resource
    private FrameUserService userService;

    @Resource
    private RedisDao redisDao;

    @MyJwtAnnotation(value = "selectUserByPrimaryKey")
    @RequestMapping(value = "/selectUserByPrimaryKey", method = RequestMethod.POST)
    public String selectUserByPrimaryKey(@RequestBody String params, HttpServletRequest request) {
        JSONObject jsonObject = JSON.parseObject(params);
        String userid = jsonObject.getString("userid");
        FrameUser user = userService.selectByPrimaryKey(Integer.parseInt(userid));
        return JSONObject.toJSONString(user);
    }

}
