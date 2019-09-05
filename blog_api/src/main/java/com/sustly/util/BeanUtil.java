package com.sustly.util;

import java.lang.reflect.Field;

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

        Field[] srcClassFields = srcClass.getDeclaredFields();
        Field[] descClassFields = descClass.getDeclaredFields();

        for (int i = 0; i < descClassFields.length; i++) {
            descClassFields[i].setAccessible(true);
            Object o = descClassFields[i].get(desc);
            if(o != null && !"".equals(o.toString().trim())){
                srcClassFields[i].setAccessible(true);
                srcClassFields[i].set(src, o);
            }
        }

        return src;
    }
}