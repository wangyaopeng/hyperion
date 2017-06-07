package com.alicp.middleware.hyperion;

/**
 * Created by fanchao on 2017/4/10.
 */
public class OrderResponse<T> {
    
    private boolean success;
    private String errCode;
    private String errMsg;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public OrderResponse<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getErrCode() {
        return errCode;
    }

    public OrderResponse<T> setErrCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public OrderResponse<T> setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public T getData() {
        return data;
    }

    public OrderResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "success=" + success +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
