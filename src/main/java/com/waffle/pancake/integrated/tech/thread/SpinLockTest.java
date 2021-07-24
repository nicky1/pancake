package com.waffle.pancake.integrated.tech.thread;

import java.util.concurrent.TimeUnit;

/**
 * 测试自定义自旋锁,基于CAS
 *
 * @author yixiaoshuang
 * @date 2020/12/13 11:57
 */
public class SpinLockTest {

    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();

        // 模拟自定义的自旋锁(基于CAS)实现测试。t1线程通过cas获取到锁,线程暂停5s,此时t2线程通过cas获取不到锁,进入自旋等待。
        // t1线程5s暂停结束后,释放锁。
        // t2线程自旋获取到锁，打印 t1 go。暂停3s后，t2线程释放锁。

        new Thread(() -> {
            lock.lock();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();
            System.out.println("t2 go ");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }, "t2").start();

    }


}
