# novice-parent
1. Spring，Mybatis，SpringMVC搭建后台框架。

2. Maven进行项目的构建和多Module项目的拆分。

3. 验证用户的登录信息，使用JWT生成token，key为用户的id，value为token的值。
> 客户端将token存放在localstorage，token不设置过期时间。服务端存放到Redis中，设置过期时间。

4. 配置拦截器拦截所有请求，放行登录和注册。
```
<!-- 配置拦截器 -->
    <mvc:interceptors>
        <mvc:interceptor>
            <!-- 拦截所有的请求，这个必须写在前面，也就是写在【不拦截】的上面 -->
            <mvc:mapping path="/**"/>
            <!-- 但是排除下面这些，也就是不拦截请求 -->
            <!--<mvc:exclude-mapping path="/index.html" />-->
            <mvc:exclude-mapping path="/pages/login/login.html" />
            <mvc:exclude-mapping path="/pages/login/register.html" />
            <mvc:exclude-mapping path="/loginAction/userLogin"/>
            <mvc:exclude-mapping path="/loginAction/userRegister"/>
            <bean class="cn.novice.utils.jwt.JWTInterceptor"></bean>
        </mvc:interceptor>
    </mvc:interceptors>
```

4. 页面在发送ajax请求后台数据时，携带localstorage存放的token，放到Headers中的Authorization。页面跳转是自定位放行规则，如tc不为空。
```
public class JWTInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每个请求都检测header
        String authorization = request.getHeader("Authorization");
        //页面跳转后增加token，如果有则放通
        if (StringUtils.isBlank(authorization)) {
            authorization = request.getParameter("tc");
        }
        String contexPath = request.getContextPath();
        if (authorization == null) {
            response.sendRedirect(contexPath + "/pages/login/login.html");
            return false;
        } else
            return true;
    }
}
```
5. 页面发送ajax请求数据时，通过JWT反解出用户id，查询Redis。
- 如果有查询结果并和Headers中的token相同，就返回数据，并刷新过期时间。
- 如果有查询结果，单和Headers中的token不相同，说明中间有其他用户登录，刷新了token。
- 没有查询结果，说明token已过期。
```
@RequestMapping(value = "/getUserListString", method = RequestMethod.POST)
    public String getUserLisString(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String uid = JwtUtil.getUid(token);
        if (StringUtils.isBlank(uid)) {
            return "token不正确";
        }
        String result = redisDao.get(uid);
        if (StringUtils.isBlank(result))
            return "token已过期";
        else if (!token.equals(result)) {
            return "该账号已在别处登录";
        } else {
            redisDao.setExpire(uid, token);
            return JSONObject.toJSONString(userService.getUserList());
        }
    }
```
# novice-parent
