package com.ziroom.aquarius.jvmdemo;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

    }
}
