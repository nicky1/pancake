package com.waffle.pancake.integrated.tech.thread.threadlocal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalTest {

    //1.通过匿名内部类覆盖ThreadLocal的initialValue方法,指定初始值
    private ThreadLocal<Integer> seqNum = ThreadLocal.withInitial(() -> 0);

    //2.获取下一个序列值
    public int getSeqNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        ThreadLocalTest sn = new ThreadLocalTest();
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends Thread {

        private ThreadLocalTest sn;

        public TestClient(ThreadLocalTest sn) {
            this.sn = sn;
        }

        @Override
        public void run() {
            //每个线程打出3个序列值
            for (int i = 0; i < 3; i++) {
                log.info("thread [" + Thread.currentThread().getName() + "] sn[" + sn.getSeqNextNum() + "]");
            }
        }
    }

}
