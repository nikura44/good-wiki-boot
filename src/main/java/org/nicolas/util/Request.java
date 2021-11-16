package org.nicolas.util;

import java.io.Serializable;

public class Request<T> implements Serializable {

    private T reqBody;
    private String reqMsgAuth;

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
