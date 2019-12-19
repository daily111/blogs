package com.dj.tool;

public class Parameters<T> {
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



    @Override
    public String toString() {
        return "Parameters{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
    public static Parameters ok(){
        Parameters para = new Parameters();
        para.setCode("0000");
        return para;
    }
    public static Parameters fail(){
        Parameters para =new Parameters();
        para.setCode("9999");
        return para;
    }
    public static Parameters denied(){
        Parameters para =new Parameters();
        para.setCode("7777");
        para.setMsg(Constant.ACCESS_DENIED);
        return para;
    }




}
