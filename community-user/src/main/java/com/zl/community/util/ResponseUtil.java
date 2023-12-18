package com.zl.community.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zl.community.common.ResultCode;
import com.zl.community.exception.BusinessException;
import com.zl.community.exception.SecurityException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : ZL
 */
@Slf4j
public class ResponseUtil {
    /**
     * 往 response 写出 json
     *
     * @param response 响应
     * @param code   状态
     * @param data     返回数据
     */
    public static void renderJson(HttpServletResponse response, ResultCode code, Object data) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(200);

            response.getWriter()
                    .print(JacksonUtils.toJson(ResultUtils.ofCode(code,data)));
        } catch (IOException e) {
            log.error("Response写出JSON异常",e);
        }
    }
    /**
     * 往 response 写出 json
     *
     * @param response  响应
     * @param exception 异常
     */
    public static void renderJson(HttpServletResponse response, BusinessException exception) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);

            response.getWriter()
                    .write(JacksonUtils.toJson(ResultUtils.ofException(exception)));
        } catch (IOException e) {
            log.error("Response写出JSON异常，", e);
        }
    }
}
