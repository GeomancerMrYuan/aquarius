package com.ziroom.aquarius.common.util;

import java.util.UUID;

/**
 * @Classname UUIDUtil
 * @Description TODO
 * @Date 2020-05-13 10:18
 * @Created by yuanpeng
 */
public class UUIDUtil {
    /**
     * 获取32位UUID
     */
    public static String UUID32() {
        return UUID64().replace("-", "");
    }
    /**
     * 获取UUID:默认64为位
     */
    public static String UUID64() {
        return UUID.randomUUID().toString();
    }

}