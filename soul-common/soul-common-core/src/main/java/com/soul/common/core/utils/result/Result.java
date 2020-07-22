package com.soul.common.core.utils.result;

import com.soul.common.core.global.enums.GlobalEnums;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName Soul-Cloud
 * @ClassName Result
 * @Description 统一返回参数
 * @Author WangDong
 * @Date 2020/6/1 3:39 下午
 * @Version 1.0.0
 **/
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -8031438847417769246L;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 返回处理消息
     */
    private String message;

    /**
     * 时间戳
     */
    private long timestamp;

    /**
     * 返回数据对象 data
     */
    private T result;

    public Result() {
        this.code = GlobalEnums.SUCCESS.getCode();
        this.message = GlobalEnums.SUCCESS.getDesc();
        this.timestamp = System.currentTimeMillis();
        this.result = null;
    }

    public static Result success() {
        return new Result();
    }

    public static <T> Result <T> success(T t) {
        Result<T> r = new Result<>();
        r.setResult(t);
        return r;
    }

    public static <T> Result <T> success(String message, T t) {
        Result<T> r = new Result<>();
        r.setMessage(message);
        r.setResult(t);
        return r;
    }

    public static <T> Result <T> fail() {
        return fail(GlobalEnums.FAIL.getDesc());
    }

    public static <T> Result <T> fail(String message) {
        return fail(GlobalEnums.FAIL.getCode(), message);
    }

    public static <T> Result <T> fail(Integer code, String msg) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }
}
