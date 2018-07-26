package org.delphy.tokenherocms.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.delphy.tokenherocms.common.EnumError;
import org.delphy.tokenherocms.exception.DefaultException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author mutouji
 */
@Slf4j
@Component
public class OkHttpUtil {
    private OkHttpClient okHttpClient;

    public OkHttpUtil(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }
    /**
     *
     * @param url url
     * @param queries param?
     * @return string
     */
    public String get(String url, Map<String, String> queries) {
        String responseBody = "";
        StringBuilder stringBuilder = new StringBuilder(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            for (Map.Entry entry : queries.entrySet()) {
                if (firstFlag) {
                    stringBuilder.append("?");
                    firstFlag = false;
                } else {
                    stringBuilder.append("&");
                }
                stringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(stringBuilder.toString())
                .build();

        try (Response response = okHttpClient.newCall(request).execute()){
            int status = response.code();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    return response.body().string();
                } else {
                    return responseBody;
                }
            } else {
                // TODO: if failed, we should throw exception?
                throw new DefaultException(EnumError.ORACLE_CODE);
            }
        } catch (Exception e) {
            log.error("okhttp3 put error >> ex = {}", ExceptionUtils.getStackTrace(e));
        }
        return responseBody;
    }

    /**
     *
     * @param url url
     * @param params params
     * @return return
     */
    public String post(String url, Map<String, String> params) {
        String responseBody = "";
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    return response.body().string();
                } else {
                    return responseBody;
                }
            }
        } catch (Exception e) {
            log.error("okhttp3 post error >> ex = {}", ExceptionUtils.getStackTrace(e));
        }
        return responseBody;
    }
//    /**
//     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"}
//     * 参数一：请求Url
//     * 参数二：请求的JSON
//     * 参数三：请求回调
//     */
//    public String postJsonParams(String url, String jsonParams) {
//        String responseBody = "";
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = okHttpClient.newCall(request).execute();
//            int status = response.code();
//            if (response.isSuccessful()) {
//                return response.body().string();
//            }
//        } catch (Exception e) {
//            log.error("okhttp3 post error >> ex = {}", ExceptionUtils.getStackTrace(e));
//        }
//        return responseBody;
//    }
//
//    /**
//     * Post请求发送xml数据....
//     * 参数一：请求Url
//     * 参数二：请求的xmlString
//     * 参数三：请求回调
//     */
//    public String postXmlParams(String url, String xml) {
//        String responseBody = "";
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//        Response response = null;
//        try {
//            OkHttpClient okHttpClient = new OkHttpClient();
//            response = okHttpClient.newCall(request).execute();
//            int status = response.code();
//            if (response.isSuccessful()) {
//                return response.body().string();
//            }
//        } catch (Exception e) {
//            log.error("okhttp3 post error >> ex = {}", ExceptionUtils.getStackTrace(e));
//        } finally {
//            if (response != null) {
//                response.close();
//            }
//        }
//        return responseBody;
//    }
}
