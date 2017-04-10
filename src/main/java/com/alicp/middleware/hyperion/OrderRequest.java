package com.alicp.middleware.hyperion;

/**
 * Created by fanchao on 2017/4/10.
 */
public class OrderRequest {
    
    private long amount;
    private long uid;
    private String nick;
    private String clientIp;
    private String appKey;

    public long getAmount() {
        return amount;
    }

    public OrderRequest setAmount(long amount) {
        this.amount = amount;
        return  this;
    }

    public long getUid() {
        return uid;
    }

    public OrderRequest setUid(long uid) {
        this.uid = uid;
        return  this;
    }

    public String getNick() {
        return nick;
    }

    public OrderRequest setNick(String nick) {
        this.nick = nick;
        return  this;
    }

    public String getClientIp() {
        return clientIp;
    }

    public OrderRequest setClientIp(String clientIp) {
        this.clientIp = clientIp;
        return  this;
    }

    public String getAppKey() {
        return appKey;
    }

    public OrderRequest setAppKey(String appKey) {
        this.appKey = appKey;
        return  this;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "amount=" + amount +
                ", uid=" + uid +
                ", nick='" + nick + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", appKey='" + appKey + '\'' +
                '}';
    }
}
