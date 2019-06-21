package com.sustly.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
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
    @Pointcut(value = "execution(* com.sustly.dao..*(..))")
    public void pointCut() {
    }

    /**
     * 后置通知
     * @param joinPoint
     * @param retVal
     */
    @AfterReturning(pointcut = "pointCut()", argNames = "joinPoint, retVal", returning = "retVal")
    public void afterReturn(JoinPoint joinPoint, Object retVal) {
        String className = joinPoint.getSignature().getName();
        className = className.substring(className.lastIndexOf(".") + 1);
        className = getMethodChineseName(className);
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


    /**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
     *
     * @param args
     * @param mName
     * @return
     */
    public String optionContent(Object[] args, String mName) {
        if (args == null) {
            return null;
        }
        StringBuilder rs = new StringBuilder();
        rs.append(mName);
        String className = null;
        int index = 1;
        // 遍历参数对象
        for (Object info : args) {
            // 获取对象类型
            className = info.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            rs.append("[参数").append(index).append("，类型:").append(className).append("，值:");
            // 获取对象的所有方法
            Method[] methods = info.getClass().getDeclaredMethods();
            // 遍历方法，判断get方法
            for (Method method : methods) {
                String methodName = method.getName();
                // 判断是不是get方法
                if (!methodName.contains("get")) {
                    continue;
                }
                Object rsValue = null;
                try {
                    // 调用get方法，获取返回值
                    rsValue = method.invoke(info);
                } catch (Exception e) {
                    continue;
                }
                // 将值加入内容中
                rs.append("(").append(methodName).append(":").append(rsValue).append(")");
            }
            rs.append("]");
            index++;
        }
        return rs.toString();
    }

    /**
     * 判断操作的中文名
     *
     * @param methodName methodName
     * @return String
     */
    private String getMethodChineseName(String methodName) {
        if (methodName.startsWith("get") || methodName.startsWith("find")) {
            return "查找数据";
        }else if (methodName.startsWith("delete")){
            return "删除数据";
        } else if (methodName.startsWith("update")){
            return "更新数据";
        } else if (methodName.startsWith("add") || methodName.startsWith("save")){
            return "添加数据";
        } else {
            return "";
        }
    }

}
