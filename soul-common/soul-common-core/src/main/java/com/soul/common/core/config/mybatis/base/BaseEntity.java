package com.soul.common.core.config.mybatis.base;

import com.soul.common.core.global.constants.GlobalConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * BaseEntity
 * 公共实体属性
 * @author wangdong
 * @version 1.0.0
 * @date 2020/7/20 23:50
 **/
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = -6569522044234676123L;

    /**
     * 创建者
     */
    protected Long createUserId;
    /**
     * 创建日期
     */
    protected LocalDateTime createTime;
    /**
     * 更新者
     */
    protected Long updateUserId;
    /**
     * 更新日期
     */
    protected LocalDateTime updateTime;
    /**
     * 备注
     */
    protected String remark;
    /**
     * 版本
     */
    protected Integer version;
    /**
     * 删除标记（0：正常；1：删除;）
     */
    protected Integer delFlag;

    public void beforeInsert(){
        this.createTime = LocalDateTime.now();
        this.createUserId = null;
        this.version = 0;
        this.delFlag = GlobalConstants.DEL_FLAG_NORMAL;
    }

    public void beforeUpdate(){
        this.updateTime = LocalDateTime.now();
        this.updateUserId = null;
    }

    public void beforeDelete(){
        this.updateTime = LocalDateTime.now();
        this.delFlag = GlobalConstants.DEL_FLAG_DELETE;
    }

}
