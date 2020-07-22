package com.soul.common.pojo.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @ProjectName Soul-Cloud
 * @ClassName SoulUserEntity
 * @Description 用户表
 * @Author WangDong
 * @Date 2020/6/1 3:34 下午
 * @Version 1.0.0
 **/
@Data
@Alias("SoulUserEntity")
@AllArgsConstructor
@NoArgsConstructor
public class SoulUserEntity {

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
