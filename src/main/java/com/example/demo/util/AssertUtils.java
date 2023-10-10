package com.example.demo.util;

import org.apache.ibatis.ognl.ObjectElementsAccessor;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
public class AssertUtils {

    public static void notNull(Object o){
        notNull(o, "不能为空");
    }

    public static void isNull(Object o){
        isNull(o, "必须为空");
    }
    public static void isNull(Object o, String message){
        if(o != null){
            throw new RuntimeException(message);
        }
    }
    public static void notNull(Object o, String message){
        if(o == null){
            throw new RuntimeException(message);
        }
    }
}
