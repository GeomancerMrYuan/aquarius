package com.ziroom.aquarius;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2019年06月18日 15:17
 * @since 1.0
 */
public class Demo {
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        System.out.println(obj.getString("msg"));
    }
}
