package cn.novice.controller.login;

import cn.novice.entity.FrameUser;
import cn.novice.service.frameuser.FrameUserService;
import cn.novice.service.redis.RedisDao;
import cn.novice.utils.jwt.JwtUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/loginController")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private RedisDao redisDao;

    @Resource
    private FrameUserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userLogin(@RequestBody String params) {
        JSONObject jsonObject = JSON.parseObject(params);
        String username = jsonObject.getString("username");
        String passwd = jsonObject.getString("password");
        FrameUser frameUser = userService.selectByNamePasswd(username, passwd);
        if (frameUser != null) {
            //自定义Redis存储主键
            String uid = "uid_" + frameUser.getUserid();
            //返回token
            String token = JwtUtil.sign(frameUser.getUsername(), uid);
            redisDao.setExpire(uid, token);
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("token", token);
            return jsonobject.toString();
        } else
            return "用户名和密码不正确";
    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public String userRegister(@RequestBody String params) {
        JSONObject jsonObject = JSON.parseObject(params);
        String username = jsonObject.getString("username");
        String passwd = jsonObject.getString("password");
        JSONObject object = new JSONObject();
        FrameUser frameUser = userService.selectByUsername(username);
        if (frameUser != null) {
            object.put("user", "exist");
        } else {
            frameUser = new FrameUser();
            frameUser.setUsername(username);
            frameUser.setPasswd(passwd);
            frameUser.setCreatedate(new Date());
            userService.insert(frameUser);
            object.put("user", "insert");
        }
        return object.toString();
    }

}
