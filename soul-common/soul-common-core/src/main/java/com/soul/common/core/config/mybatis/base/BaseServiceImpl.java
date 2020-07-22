package com.soul.common.core.config.mybatis.base;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * BaseServiceImpl
 * 重写基础CRUD
 * @author wangdong
 * @version 1.0.0
 * @date 2020/7/21 00:00
 **/
public class BaseServiceImpl<M extends BaseDao<T>, T> extends ServiceImpl<BaseDao<T>, T>
        implements BaseService<T> {

}
