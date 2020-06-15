package com.ruoyi.book.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Hqinjun
 * @date 2020/6/14 23:56
 */
@Data
public class BookResult<M> {

    /** 返回状态【0-成功，1-业务失败，999-表示系统异常】*/
    @JSONField(ordinal = 1)
    private int code;
    /**返回信息*/
    @JSONField(ordinal = 2)
    private String message;
    /**返回数据实体*/
    @JSONField(ordinal = 3)
    private M data;


    private BookResult(){}


    public static<M> BookResult success(M m){
        BookResult r = new BookResult();
        r.setCode(0);
        r.setMessage("success");
        r.setData(m);

        return r;
    }

    public static<M> BookResult success(String msg){
        BookResult r = new BookResult();
        r.setCode(0);
        r.setMessage(msg);

        return r;
    }

    public static<M> BookResult serviceFail(String msg){
        BookResult r = new BookResult();
        r.setCode(1);
        r.setMessage(msg);

        return r;
    }

    public static<M> BookResult appFail(String msg){
        BookResult r = new BookResult();
        r.setCode(999);
        r.setMessage(msg);

        return r;
    }

    public static<M> BookResult error(int code,String msg){
        BookResult r = new BookResult();
        r.setCode(code);
        r.setMessage(msg);

        return r;
    }

}
