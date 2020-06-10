package com.ziroom.aquarius.jvmdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Classname Demo2
 * @Description TODO
 * @Date 2020-05-19 16:01
 * @Created by yuanpeng
 */
public class Demo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.sort(list);
        /**
         * @Description 双轴快速排序
         * @Date 2020-05-19 16:10
         * @Created by yuanpeng
         */
        int[] arr={1,2,3,4,5,6,0,9,3,4};
        Arrays.sort(arr);

        JSONObject object = new JSONObject();
        ArrayList<Object> list1 = new ArrayList<>();
        object.put("list", list1);
        JSON.toJSONString(object);

       Map<String, Object> map = new HashMap<>();
        map.put("list", list1);
        JSON.toJSONString(map);


    }
}
