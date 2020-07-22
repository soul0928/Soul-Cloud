package com.soul.auth.modules.dto;

import lombok.Data;

/**
 * @author wangdong
 * @version 1.0.0
 * @date 2020/7/22 11:07
 **/
@Data
public class UserDto {


    private String userName;
    private String password;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked= true;
    private boolean credentialsNonExpired= true;
    private boolean enabled= true;

}
