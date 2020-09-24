package com.zksir.eshop.inventory.thread;

import com.zksir.eshop.inventory.request.Request;
import com.zksir.eshop.inventory.request.RequestQueue;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * 执行请求的工作线程
 */
public class RequestProcessorThread implements Callable<Boolean> {
    /**
     * 自己监控的内存队列
     */
    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }
    @Override
    public Boolean call() throws Exception {
        try {
            while (true) {
                // ArrayBlockingQueue
                // Blocking就是说明，如果队列满了，或者是空的，那么都会在执行操作的时候，阻塞住
                Request request = queue.take();
                boolean forceRfresh = request.isForceRefresh();

                // 先做读请求的去重
                if (!forceRfresh) {
                    RequestQueue requestQueue = RequestQueue.getInstance();
                    Map<Integer,Boolean> flagMap = requestQueue.getFlagMap();

                    if (true){

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
