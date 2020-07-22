package com.soul.common.core.utils.result;

import com.soul.common.core.utils.json.GsonUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName Soul-Cloud
 * @ClassName ResponseUtil
 * @Description
 * @Author WangDong
 * @Date 2020/6/1 4:38 下午
 * @Version 1.0.0
 **/
@Slf4j
public class ResponseUtil {

    /**
     *  使用response输出JSON
     * @param response response
     * @param result result
     */
    public static void out(HttpServletResponse response, Result result){

        ServletOutputStream out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            out = response.getOutputStream();
            out.write(GsonUtil.toJson(result).getBytes());
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally{
            if(out!=null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
