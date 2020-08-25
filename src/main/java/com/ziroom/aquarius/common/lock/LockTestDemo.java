package com.ziroom.aquarius.common.lock;

import com.baomidou.mybatisplus.extension.api.R;

import java.util.concurrent.*;


/**
 * @Classname LockTestDemo
 * @Description TODO
 * @Date 2020/6/17 11:05 上午
 * @Created by yuanpeng
 */
public class LockTestDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        //创建10000个任务,由是个线程执行
        LockTestDemo demo = new LockTestDemo();
        for (int i=0; i <1000; i++) {
            pool.execute(()->{
                try {
                    demo.getId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
        while (true){
            if(pool.isTerminated()){
                break;
            }
            Thread.sleep(1000);
        }
        System.out.println("最后结果为:"+i);
    }

    public static int i=0;

    /**
     * @Description 无锁
     * @Date 2020/6/17 4:44 下午
     * @Created by yuanpeng
     */
//    private static int getId() {
//        return ++i;
//    }

    /**
     * @Description synchronized锁
     * @Date 2020/6/17 4:44 下午
     * @Created by yuanpeng
     */
//    private static synchronized int getId() {
//       return ++i;
//    }

    /**
     * @Description 分布式锁
     * @Date 2020/6/17 4:45 下午
     * @Created by yuanpeng
     */
//    static Lock lock=new ZkLock();//zk基于临时节点
//    static Lock lock=new ZkSequenLock();//zk基于临时顺序节点,效率极差
//    static Lock lock=new ZkCuratorLock();//效率还可以
    static Lock lock=new RedissonLock();
    private static void getId() throws Exception {
        try {
            lock.getLock();
            ++i;
            System.out.println(i);
        } finally {
            lock.unlock();
        }

    }
}
