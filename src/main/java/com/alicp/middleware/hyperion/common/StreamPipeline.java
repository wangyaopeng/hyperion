package com.alicp.middleware.hyperion.common;

/**
 * Created by fanchao on 2017/4/7.
 */
public class StreamPipeline {

    private HandlerContext head;//pipeline head
    private HandlerContext tail;//pipeline tail

    public StreamPipeline addFirst(Handler handler) {//
        HandlerContext ctx = new HandlerContext(handler);
        HandlerContext tmp = head;
        head = ctx;
        head.setNext(tmp);
        return this;
    }

    final class HeadContext extends HandlerContext{//
        public HeadContext(Handler handler) {
            super(handler);
        }
    }
    final class HeadHandler implements Handler{
        @Override
        public void handleRequest(HandlerContext context, ContextHolder request) {
        }
    }

    public StreamPipeline() {
        head = tail = new HeadContext(new HeadHandler());
    }

    public void request(Object request){
        if(head != null){
            head.doWork(request);
        }
    }
}
