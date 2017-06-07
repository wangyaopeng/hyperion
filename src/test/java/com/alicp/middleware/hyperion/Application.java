package com.alicp.middleware.hyperion;

import com.alicp.middleware.hyperion.common.ContextHolder;
import com.alicp.middleware.hyperion.common.Handler;
import com.alicp.middleware.hyperion.common.HandlerContext;
import com.alicp.middleware.hyperion.common.StreamPipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanchao on 2017/4/7.
 */
public class Application {

    static class OrderCheck implements Handler<OrderRequest, OrderResponse> {
        @Override
        public void handleRequest(HandlerContext context, ContextHolder<OrderRequest, OrderResponse> contextHolder) {
            long flag = contextHolder.getRequest().getAmount();
            if (flag < 30) {//success, pass request to next handler
                context.write(contextHolder);
            } else if (flag < 60) {// return success
                OrderResponse<Long> orderResponse = new OrderResponse<Long>().setSuccess(true).setData(flag);
                contextHolder.getResponse().setSuccess(orderResponse);
            } else if (flag < 80) {// return failure
                OrderResponse<Long> orderResponse = new OrderResponse<Long>().setSuccess(false)
                        .setErrCode("ERROR_AMOUNT_OVER_LIMIT").setErrMsg("amount already > 60");
                contextHolder.getResponse().setSuccess(orderResponse);
            } else {// throw exception
                contextHolder.getResponse().setFailure(new IllegalArgumentException("amount cannot over 80"));
            }
        }
    }

    static class EventCheck implements Handler<OrderRequest, OrderResponse> {
        @Override
        public void handleRequest(HandlerContext context, ContextHolder<OrderRequest, OrderResponse> contextHolder) {
            context.write(contextHolder);
            //set success
            long flag = contextHolder.getRequest().getAmount() + 10000;
            OrderResponse<Long> orderResponse = new OrderResponse<Long>().setSuccess(true).setData(flag);
            contextHolder.getResponse().setSuccess(orderResponse);
        }
    }

    static class Task implements Runnable {
        private final int index;
        private final StreamPipeline pipeline;

        public Task(StreamPipeline pipeline, int index) {
            this.index = index;
            this.pipeline = pipeline;
        }

        @Override
        public void run() {
            try {
                OrderRequest request = new OrderRequest().setAmount(index * 15);
                ContextHolder<OrderRequest, OrderResponse> holder = new ContextHolder<>(request);
                holder.getResponse().addListener(future -> {
                    try {
                        System.out.println(future.get());
                    } catch (Throwable th) {
                        System.out.println(th.getMessage());
                    }
                });
                pipeline.request(holder);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        StreamPipeline pipeline = new StreamPipeline()
                .addFirst(new EventCheck()).addFirst(new OrderCheck());

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            threads.add(new Thread(new Task(pipeline, i)));
        }

        for (Thread thread : threads) {
            thread.start();
        }

    }

}
