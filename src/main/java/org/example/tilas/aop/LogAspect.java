package org.example.tilas.aop;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.tilas.mapper.OperateLogMapper;
import org.example.tilas.pojo.OperateLog;
import org.example.tilas.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author dream
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
    
    @Autowired
    private HttpServletRequest request;
    
    @Around("@annotation(org.example.tilas.anno.Log)")
    public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人的id
        //请求头中的jwt令牌，解析令牌
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //操作类名
        String claseeName = joinPoint.getTarget().getClass().getName();
        //操作方法名
        String methodName = joinPoint.getSignature().getName();
        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //调用原始目标方法
        Object result = joinPoint.proceed();
        //方法返回值
        String returnValue = JSONObject.toJSONString(result);
        //操作耗时
        long end = System.currentTimeMillis();
        Long costTime = end - begin;
        //记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,claseeName,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        log.info("AOP记录操作日志：{}",operateLog);
        return result;
    }
}
