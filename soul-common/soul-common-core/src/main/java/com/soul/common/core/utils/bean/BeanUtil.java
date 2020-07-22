package com.soul.common.core.utils.bean;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName Soul-Cloud
 * @ClassName BeanUtil
 * @Description bean转换工具类
 * @Author WangDong
 * @Date 2020/5/29 5:55 下午
 * @Version 1.0.0
 **/
@Slf4j
public class BeanUtil {

    /**
     * @description 对象转换
     * @author wangdong
     * @date 2020/5/29 5:59 下午
     * @param source 原对象
     * @param target 目标对象
     * @return void
     **/
    public static void copyProperties(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }

    /**
     * @Description : 对象转换
     * @author wangdong
     * @date 2020/5/29 5:59 下午
     * @param source 原对象
     * @param clazz 目标对象类型
     * @return T
     **/
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T target = null;
        try {
            target = clazz.newInstance();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        copyProperties(source, target);
        return target;
    }

    /**
     * @description 给定集合转换为指定类型的集合
     * @author wangdong
     * @date 2020/5/29 6:00 下午
     * @param sourceList
     * @param clazz
     * @return java.util.List<T>
     **/
    public static <S, T> List<T> copyList(List<S> sourceList, Class<T> clazz) {
        if (sourceList == null || sourceList.isEmpty()) {
            return null;
        }
        List<T> targetList = new ArrayList<>(sourceList.size());
        for (S item : sourceList) {
            T target = copyProperties(item, clazz);
            targetList.add(target);
        }
        return targetList;
    }

}
