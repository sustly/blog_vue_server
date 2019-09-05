package com.sustly.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author admin
 */
@Component
@Slf4j
@Aspect
public class LoggerAspect {
    /**
     * 定义切点位置
     */
    @Pointcut(value = "execution(* com.sustly.*.*(..))")
    public void pointCut() {
    }

    /**
     * 后置通知
     * @param joinPoint joinPoint
     * @param retVal retVal
     */
    @AfterReturning(pointcut = "pointCut()", argNames = "joinPoint, retVal", returning = "retVal")
    public void afterReturn(JoinPoint joinPoint, Object retVal) {
        String className = joinPoint.getSignature().getName();
        className = className.substring(className.lastIndexOf(".") + 1);
        //日志记录连接点方法签名
        log.info(className+":"+joinPoint.getSignature().toString());
        //判断参数
        Object[] args = joinPoint.getArgs();
        StringBuilder rs = new StringBuilder();
        String s = "";
        if (args.length > 0) {
            for (Object arg : args) {
                if (arg == null){
                    continue;
                }
                if (arg instanceof Object[]) {
                    rs.append(Arrays.toString(((Object[]) arg))).append(", ");
                } else {
                    rs.append(arg.toString()).append(", ");
                }
            }
            s = rs.toString().substring(0, rs.toString().length() - 2);
        }

        log.info("参数：[{}]  返回值：[{}]", s ,retVal);

    }

}