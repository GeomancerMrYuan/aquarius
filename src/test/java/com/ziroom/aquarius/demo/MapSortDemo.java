package com.ziroom.aquarius.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2020年04月15日 10:36
 * @since 1.0
 */
@Slf4j
public class MapSortDemo {

    public static void main(String[] args) {
        method7();
    }

    private static void method7() {
        try(BufferedReader br = new BufferedReader(new FileReader("/Users/yuanpeng/IdeaProjects/aquarius/src/test/java/com/ziroom/aquarius/demo/c.txt")); BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"))) {
           Map<String,String> hashMap = new TreeMap<String,String>();
            String lean;
            System.out.println("排序前+++++++++++++++++++++++++++++");
            while ((lean=br.readLine())!=null){
                System.out.println(lean);
                //将读取的值存入hashMap中
                String[] strs = lean.split("\\.");
                hashMap.put(strs[0],strs[1]);
            }
            //然后将hashMap读取的值再读入文件中

            System.out.println("排序后+++++++++++++++++++++++++++++");
            Set<String> sets = hashMap.keySet();
            for (String key : sets) {
                System.out.println(key+"."+hashMap.get(key));
                bw.write(key+"."+hashMap.get(key));
                bw.newLine();
            }
        } catch (IOException e) {
            log.info("出错了");
            e.printStackTrace();
        }
    }
}
