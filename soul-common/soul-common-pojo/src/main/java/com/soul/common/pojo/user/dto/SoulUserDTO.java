package com.soul.common.pojo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName Soul-Cloud
 * @ClassName SoulUserEntity
 * @Description 用户表
 * @Author WangDong
 * @Date 2020/6/1 3:34 下午
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoulUserDTO {

    /**
     * 用户ID
     */
    private Long id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private Long password;


}
