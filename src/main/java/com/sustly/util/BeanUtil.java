package com.sustly.util;

import java.lang.reflect.Method;

/**
 * 该工具类可实现前端传来部分属性更新，而不覆盖已有不更新的属性
 * @author liyue
 * @date 2019/3/26 16:53
 */
public class BeanUtil {

    /**
     * 解决更新时，源对象被覆盖
     * @param src 数据库查出的源对象数据
     * @param desc 前端传的需要更新的数据
     * @return 更新后的对象
     * @throws Exception 源对象与目标对象类型不一致
     */
    public static Object updateBean(Object src,Object desc) throws Exception {
        Class<?> srcClass = src.getClass();
        Class<?> descClass = desc.getClass();
        if(!descClass.equals(srcClass)){
            throw new Exception("源对象与目标对象类型不一致！");
        }
        Method[] descClassDeclaredMethods = descClass.getDeclaredMethods();
        Method[] srcClassDeclaredMethods = srcClass.getDeclaredMethods();
        for(Method descMethod : descClassDeclaredMethods){
            if (descMethod.getName().startsWith("get")){
                Object invoke = descMethod.invoke(desc);
                if ((invoke != null) && !"".equals(invoke.toString().trim())){
                    String methodSetMethod = "set"+descMethod.getName().substring(3);
                    for (Method srcMethod : srcClassDeclaredMethods){
                        if (srcMethod.getName().equalsIgnoreCase(methodSetMethod)){
                            srcMethod.invoke(src,invoke);
                        }
                    }
                }
            }
        }
        return src;
    }
}
