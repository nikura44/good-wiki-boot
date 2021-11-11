package org.nicolas.entry;

public class Response<T> {
    private String respMsg;
    private T respBody;

    public Response(){}

    public Response(T respBody) {
        this.respBody = respBody;
    }

    public Response(String respMsg,T respBody){
        this.respBody = respBody;
        this.respMsg = respMsg;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public T getRespBody() {
        return respBody;
    }

    public void setRespBody(T respBody) {
        this.respBody = respBody;
    }
}
