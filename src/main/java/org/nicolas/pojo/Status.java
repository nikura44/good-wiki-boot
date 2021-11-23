package org.nicolas.pojo;

public enum Status {
    //激活状态
    active("true"),
    //删除状态
    invalid("false");

    private String value;


    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
