package com.fang.backgroundapi.exception;

import lombok.Data;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/8/18 16:57
 **/
@Data
public class MyException extends Exception{

    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MyException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public MyException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public MyException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

}
