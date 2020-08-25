//package com.ziroom.aquarius.common.lock;
//
//import lombok.extern.slf4j.Slf4j;
//import org.I0Itec.zkclient.IZkDataListener;
//import org.I0Itec.zkclient.ZkClient;
//
//import java.util.concurrent.CountDownLatch;
//
//@Slf4j
//public class ZkLock extends AbstractLock {
//    private static final String zkServers = "127.0.0.1:2181";
//    private static final int sessionTimeout = 8000;
//    private static final int connectionTimeout = 5000;
//
//    private static final String lockPath = "/lockPath1";
//
//
//    private ZkClient client;
//
//    public ZkLock() {
//        client = new ZkClient(zkServers, sessionTimeout, connectionTimeout);
//        log.info("zk client 连接成功:{}",zkServers);
//    }
//
//    @Override
//    protected void waitLock() {
//        CountDownLatch latch = new CountDownLatch(1);
//
//        IZkDataListener listener = new IZkDataListener() {
//            @Override
//            public void handleDataDeleted(String dataPath) throws Exception {
//                System.out.println("监听到节点被删除");
//                if (latch != null) {
//                    latch.countDown(); // 计数器为0的情况，await 后面的继续执行
//                }
//            }
//            @Override
//            public void handleDataChange(String dataPath, Object data) throws Exception {}
//        };
//        //完成 watcher 注册
//        client.subscribeDataChanges(lockPath, listener);
//
//        //阻塞自己
//        if (client.exists(lockPath)) {
//            try {
//                latch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        //取消watcher注册
//        client.unsubscribeDataChanges(lockPath, listener);
//    }
//
//    @Override
//    protected boolean tryLock() {
//        try {
//            client.createEphemeral(lockPath);
//            System.out.println(Thread.currentThread().getName()+"获取到锁");
//        } catch (Exception e) {
//            log.error("创建失败");
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void releaseLock() {
//       client.delete(this.lockPath);
//    }
//}
