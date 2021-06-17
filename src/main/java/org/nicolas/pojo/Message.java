package org.nicolas.pojo;

public class Message {
    private Boolean isOk = false;
    private String result = "default";

    public Boolean getOk() {
        return isOk;
    }

    public void setOk(Boolean ok) {
        isOk = ok;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
