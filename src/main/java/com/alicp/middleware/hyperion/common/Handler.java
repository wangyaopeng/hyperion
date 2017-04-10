package com.alicp.middleware.hyperion.common;

/**
 * Created by fanchao on 2017/4/7.
 */
public interface Handler<R, P> {
    void handleRequest(HandlerContext context, ContextHolder<R, P> request);
}
