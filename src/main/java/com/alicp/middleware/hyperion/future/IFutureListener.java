package com.alicp.middleware.hyperion.future;

/**
 * Created by fanchao on 2017/4/10.
 */
public interface IFutureListener<V> {
    void operationCompleted(IFuture<V> future) throws Exception;
}
