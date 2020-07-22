package com.soul.common.core.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.soul.common.core.global.constants.GlobalConstants;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName Soul-Cloud
 * @ClassName GsonUtil
 * @Description Gson 工具类
 * @Author WangDong
 * @Date 2020/6/1 4:44 下午
 * @Version 1.0.0
 **/
public class GsonUtil {

    /**
     *  过滤空值
     */
    private static final Gson GSON;

    static {
        GSON = new GsonBuilder()
                //当Map的key为复杂对象时,需要开启该方法
                .enableComplexMapKeySerialization()
                //当字段值为空或null时，依然对该字段进行转换
                //.serializeNulls()
                //打开Export注解，但打开了这个注解,副作用，要转换和不转换都要加注解
                //.excludeFieldsWithoutExposeAnnotation()
                //序列化日期格式  "yyyy-MM-dd"
                .setDateFormat(GlobalConstants.FORMAT)
                //自动格式化换行
                //.setPrettyPrinting()
                //防止特殊字符出现乱码
                .disableHtmlEscaping()
                .create();
    }


    /**
     * @description 获取gson解析器
     * @author wangdong
     * @date 2020/6/1 4:50 下午
     * @return com.google.gson.Gson
     **/
    public static Gson getGson() {
        return GSON;
    }

    /**
     * @description 根据对象返回json  过滤空值字段
     * @author wangdong
     * @date 2020/6/1 4:53 下午
     * @param obj obj
     * @return java.lang.String
     **/
    public static String toJson(Object obj) {
        return getGson().toJson(obj);
    }

    /**
     * @description 将字符串转化对象
     * @author wangdong
     * @date 2020/6/1 4:53 下午
     * @param json     源字符串
     * @param cls 目标对象类型
     * @return T
     **/
    public static <T> T strToJavaBean(String json, Class<T> cls) {
        return getGson().fromJson(json, cls);
    }

    /**
     * 将json转化为对应的实体对象
     * new TypeToken<List<T>>() {}.getType()
     * new TypeToken<Map<String, T>>() {}.getType()
     * new TypeToken<List<Map<String, T>>>() {}.getType()
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        return getGson().fromJson(json, typeOfT);
    }

    /**
     * @description 转成list
     * @author wangdong
     * @date 2020/6/1 4:55 下午
     * @param json json
     * @param cls cls
     * @return java.util.List<T>
     **/
    public static <T> List<T> strToList(String json, Class<T> cls) {
        return getGson().fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * @description 转成list中有map的
     * @author wangdong
     * @date 2020/6/1 4:56 下午
     * @param json json
     * @return java.util.List<java.util.Map<java.lang.String,T>>
     **/
    public static <T> List<Map<String, T>> strToListMaps(String json) {
        return getGson().fromJson(json, new TypeToken<List<Map<String, String>>>() {
        }.getType());
    }

    /**
     * @description 转成map
     * @author wangdong
     * @date 2020/6/1 4:57 下午
     * @param json json
     * @return java.util.Map<java.lang.String,T>
     **/
    public static <T> Map<String, T> strToMaps(String json) {
        return getGson().fromJson(json, new TypeToken<Map<String, T>>() {
        }.getType());
    }
}
