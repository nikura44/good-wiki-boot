package org.nicolas.util;

import java.io.Serializable;

public class Response <T> implements Serializable {
    private String respMsgAuth;
    private T respBody;

    public Response() {

    }

    public Response(T respBody) {
        this.respBody = respBody;
    }

    public String getRespMsgAuth() {
        return respMsgAuth;
    }

    public void setRespMsgAuth(String respMsgAuth) {
        this.respMsgAuth = respMsgAuth;
    }

    public T getRespBody() {
        return respBody;
    }

    public void setRespBody(T respBody) {
        this.respBody = respBody;
    }
}
