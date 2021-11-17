package org.nicolas.util;

import java.io.Serializable;

public class Request<T> implements Serializable {

    private T reqBody;
    private String reqMsgAuth;
    private Integer reqInt;

    public Integer getReqInt() {
        return reqInt;
    }

    public void setReqInt(Integer reqInt) {
        this.reqInt = reqInt;
    }

    public T getReqBody() {
        return reqBody;
    }

    public void setReqBody(T reqBody) {
        this.reqBody = reqBody;
    }

    public String getReqMsgAuth() {
        return reqMsgAuth;
    }

    public void setReqMsgAuth(String reqMsgAuth) {
        this.reqMsgAuth = reqMsgAuth;
    }
}
