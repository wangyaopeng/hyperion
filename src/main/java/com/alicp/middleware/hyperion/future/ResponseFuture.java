package com.alicp.middleware.hyperion.future;

/**
 * Created by fanchao on 2017/4/10.
 */
public class ResponseFuture<V> extends AbstractFuture<V> {

    @Override
    public IFuture<V> setSuccess(V result) {
        return super.setSuccess(result);
    }

    @Override
    public IFuture<V> setFailure(Throwable cause) {
        return super.setFailure(cause);
    }
}
