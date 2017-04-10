package com.alicp.middleware.hyperion.common;

import com.alicp.middleware.hyperion.future.ResponseFuture;

/**
 * Created by fanchao on 2017/4/10.
 */
public class ContextHolder<R, P> {

    private  R request;

    private ResponseFuture<P> response;

    public ContextHolder(R request) {
        this.request = request;
        this.response = new ResponseFuture<P>();
    }

    public R getRequest() {
        return request;
    }

    public void setRequest(R request) {
        this.request = request;
    }

    public ResponseFuture<P> getResponse() {
        return response;
    }

    public void setResponse(ResponseFuture<P> response) {
        this.response = response;
    }
}
