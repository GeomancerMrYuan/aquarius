package com.ziroom.aquarius.demo;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.field.FieldDescription;

import java.io.*;
import java.util.*;

/**
 * @Description
 * @Date 2020/4/9 9:58
 * @Param
 * @return
 * @Author lcl
 **/

public class MyTest {

    static int  x=100;

    public int calc(){
        int a=100;
        int b=100;
        int c=300;
        return (a+b)*c;
    }

}