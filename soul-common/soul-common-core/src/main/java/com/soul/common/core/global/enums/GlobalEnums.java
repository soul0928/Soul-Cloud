package com.soul.common.core.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName Soul-Cloud
 * @ClassName GlobalEnums
 * @Description 全局
 * @Author WangDong
 * @Date 2020/6/1 3:46 下午
 * @Version 1.0.0
 **/
@Getter
@AllArgsConstructor
public enum GlobalEnums {

    /**
     * 系统执行成功
     */
    SUCCESS(200,  "处理成功!!!"),
    /**
     * 系统执行失败
     */
    FAIL(500, "系统异常"),
    ;

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;


}
