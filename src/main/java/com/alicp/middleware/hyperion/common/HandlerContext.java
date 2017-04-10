package com.alicp.middleware.hyperion.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fanchao on 2017/4/7.
 */
public class HandlerContext {

    private ExecutorService executor = Executors.newCachedThreadPool();//thread pool
    private Handler handler;
    private HandlerContext next;//next handler ref

    public HandlerContext(Handler handler) {
        this.handler = handler;
    }

    public void setNext(HandlerContext ctx) {
        this.next = ctx;
    }

    @SuppressWarnings("unchecked")
    public void doWork(final Object request) {
        if (next != null) {
            executor.submit(() -> handler.handleRequest(next, (ContextHolder) request));
        }
    }

    public void write(Object request){
        doWork(request);
    }

}
