//package com.ziroom.aquarius.system.controller;
//
///**
// * @Classname LockDemo
// * @Description TODO
// * @Date 2020/6/17 5:17 下午
// * @Created by yuanpeng
// */
//
//import com.baomidou.mybatisplus.extension.api.R;
//import com.ziroom.aquarius.AquariusApplicationTests;
//import com.ziroom.aquarius.common.lock.Lock;
//import com.ziroom.aquarius.common.lock.ZkCuratorLock;
////import com.ziroom.aquarius.common.lock.ZkLock;
////import com.ziroom.aquarius.common.lock.ZkSequenLock;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//
//import java.util.concurrent.*;
//
//
///**
// * @Classname LockTestDemo
// * @Description TODO
// * @Date 2020/6/17 11:05 上午
// * @Created by yuanpeng
// */
//@Slf4j
//public class LockDemo extends AquariusApplicationTests {
//    @Test
//    public void test() throws ExecutionException, InterruptedException {
//        ExecutorService pool = Executors.newFixedThreadPool(10);
//        //遍历1000次,每次创建4个线程,去拿主键id
//        FutureTask task = null;
//        for (int i = 0; i < 100; i++) {
//            task = new FutureTask<>(new Callable<Integer>() {
//                @Override
//                public Integer call() throws Exception {
//                    return getId();
//                }
//            });
//            pool.execute(task);
//        }
//        pool.shutdown();
//        while (true) {
//            if (pool.isShutdown()) {
//                log.info(task.get() + "----" + i);
////                System.out.println(i);
//                break;
//            }
//            Thread.sleep(1000);
//        }
//
//    }
//
//    public static int i = 0;
//
//    /**
//     * @Description 无锁
//     * @Date 2020/6/17 4:44 下午
//     * @Created by yuanpeng
//     */
////    private static int getId() {
////        return ++i;
////    }
//
//    /**
//     * @Description synchronized锁
//     * @Date 2020/6/17 4:44 下午
//     * @Created by yuanpeng
//     */
////    private static synchronized int getId() {
////       return ++i;
////    }
//
//    /**
//     * @Description 分布式锁
//     * @Date 2020/6/17 4:45 下午
//     * @Created by yuanpeng
//     */
////    Lock lock=new ZkLock();
////    Lock lock=new ZkSequenLock();
//    Lock lock = new ZkCuratorLock();
//
//    private int getId() throws Exception {
//        try {
//            lock.getLock();
//            ++i;
//            return i;
//        } finally {
//            lock.unlock();
//        }
//    }
//}
//package com.ziroom.aquarius.system.controller;
//
///**
// * @Classname LockDemo
// * @Description TODO
// * @Date 2020/6/17 5:17 下午
// * @Created by yuanpeng
// */
//
//import com.baomidou.mybatisplus.extension.api.R;
//import com.ziroom.aquarius.AquariusApplicationTests;
//import com.ziroom.aquarius.common.lock.Lock;
//import com.ziroom.aquarius.common.lock.ZkCuratorLock;
////import com.ziroom.aquarius.common.lock.ZkLock;
////import com.ziroom.aquarius.common.lock.ZkSequenLock;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//
//import java.util.concurrent.*;
//
//
///**
// * @Classname LockTestDemo
// * @Description TODO
// * @Date 2020/6/17 11:05 上午
// * @Created by yuanpeng
// */
//@Slf4j
//public class LockDemo extends AquariusApplicationTests {
//    @Test
//    public void test() throws ExecutionException, InterruptedException {
//        ExecutorService pool = Executors.newFixedThreadPool(10);
//        //遍历1000次,每次创建4个线程,去拿主键id
//        FutureTask task = null;
//        for (int i = 0; i < 100; i++) {
//            task = new FutureTask<>(new Callable<Integer>() {
//                @Override
//                public Integer call() throws Exception {
//                    return getId();
//                }
//            });
//            pool.execute(task);
//        }
//        pool.shutdown();
//        while (true) {
//            if (pool.isShutdown()) {
//                log.info(task.get() + "----" + i);
////                System.out.println(i);
//                break;
//            }
//            Thread.sleep(1000);
//        }
//
//    }
//
//    public static int i = 0;
//
//    /**
//     * @Description 无锁
//     * @Date 2020/6/17 4:44 下午
//     * @Created by yuanpeng
//     */
////    private static int getId() {
////        return ++i;
////    }
//
//    /**
//     * @Description synchronized锁
//     * @Date 2020/6/17 4:44 下午
//     * @Created by yuanpeng
//     */
////    private static synchronized int getId() {
////       return ++i;
////    }
//
//    /**
//     * @Description 分布式锁
//     * @Date 2020/6/17 4:45 下午
//     * @Created by yuanpeng
//     */
////    Lock lock=new ZkLock();
////    Lock lock=new ZkSequenLock();
//    Lock lock = new ZkCuratorLock();
//
//    private int getId() throws Exception {
//        try {
//            lock.getLock();
//            ++i;
//            return i;
//        } finally {
//            lock.unlock();
//        }
//    }
//}
