package cn.novice.utils.aop;


import cn.novice.service.redis.RedisDao;
import cn.novice.utils.annotation.MyJwtAnnotation;
import cn.novice.utils.exception.TokenException;
import cn.novice.utils.jwt.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class JwtAspect {

    @Resource
    private RedisDao redisDao;

    //Controller层切点
    @Pointcut("@annotation(cn.novice.utils.annotation.MyJwtAnnotation)")
    public void controllerAspect() {

    }

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws TokenException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取注解内容
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        MyJwtAnnotation myJwtAnnotation = method.getAnnotation(MyJwtAnnotation.class);
        //每个请求都检测header
        String authorization = request.getHeader("Authorization");
        String uid = JwtUtil.getUid(authorization);
        if (myJwtAnnotation == null) {
            throw new TokenException("注解获取异常");
        } else {
            if (StringUtils.isBlank(uid)) {
                throw new TokenException(myJwtAnnotation.value() + "token不正确");
            }
            String result = redisDao.get(uid);
            if (StringUtils.isBlank(result))
                throw new TokenException(myJwtAnnotation.value() + "token已过期");
            else if (!authorization.equals(result)) {
                throw new TokenException(myJwtAnnotation.value() + "该账号已在别处登录");
            }
        }
        redisDao.setExpire(uid, authorization);
    }
}
