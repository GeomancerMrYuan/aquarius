package com.ziroom.aquarius.common.enums;

import lombok.Data;
import lombok.Getter;

/**
 * desc: 方法类型
 * author: xuebin
 * date: 2020/03/20 17:51
 * blog: www.cxyk.net
 * 公众号：cxyknet
 */
@Getter
public enum MethodType {
    ADD("add", "增加"),
    DELETE("delete", "删除"),
    UPDATE("update", "修改"),
    EXPORT("export", "导出"),
    SELECT("query", "查询");

    private String type;
    private String name;

    MethodType(String type, String name) {
        this.type = type;
        this.name = name;
    }
    public static String getName(String type){
        for (MethodType modelType : MethodType.values()) {
            if (modelType.getType().equals(type))
                return modelType.getName();
        }
        return null;
    }

    public static String getType(String name){
        for (MethodType modelType : MethodType.values()) {
            if (modelType.getName().equals(name))
                return modelType.getType();
        }
        return "-1";
    }
}