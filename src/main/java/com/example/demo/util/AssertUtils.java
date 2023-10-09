package com.example.demo.util;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
public class AssertUtils {

    public static void notNull(Object o){
        notNull(o, "不能为空");
    }

    public static void notNull(Object o, String message){
        if(o == null){
            throw new RuntimeException(message);
        }
    }
}
