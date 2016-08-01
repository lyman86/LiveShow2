package com.lly.app.liveshow.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * json解析
 *
 * @author Desmond
 * @version [版本号, 2015-7-22]
 */
public class JsonUtil {
    /**
     * 对象转json
     *
     * @param entity
     * @return String
     * @author Desmond 2014-10-15 上午10:40:06
     */
    public static String entityToJson(Object entity) {
        String json = null;
        try {
            json = new Gson().toJson(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * json转单个对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @param <T>
     * @return Object
     * @author Desmond 2014-10-15 上午10:40:16
     */
    public static <T> T jsonToEntity(String json, Class<T> clazz) {
        T t = null;
        try {
            t = new Gson().fromJson(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * json转对象集合
     *
     * @param json
     * @param typeToken 例：new TypeToken&lt;List&lt;Person&gt;&gt;(){}
     * @param <T>
     * @return Object
     * @author Desmond 2014-10-15 上午10:40:31
     */
    public static <T> T jsonToEntity(String json, TypeToken<T> typeToken) {
        T t = null;
        try {
            t = new Gson().fromJson(json, typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

//    public static ResponseEntity jsonToEntity(String json) {
//        ResponseEntity entity = new ResponseEntity();
//        try {
//            JSONObject jo = new JSONObject(json);
//            entity.data = jo.optString("data", "");
//            entity.mark = jo.optInt("mark", -1);
//            entity.message = jo.optString("message", "服务器数据异常");
//            entity.sys = jo.optString("sys", "");  //在PI中没有sys这个字段 给一个空值
//            //分类目录使用
//            entity.updateTime = jo.optString("updateTime", "");
//            entity.totalCount = jo.optInt("", 0);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            LogUtil.e("Exception", json);
//            entity.mark = -1;
//            entity.message = "服务器数据异常";
//        }
//        return entity;
//    }

//    public static Map jsonToMap(String json) {
//        JSONObject jsonObject = null;
//        Map<String, String> map = new HashMap<>();
//        try {
//            jsonObject = new JSONObject(json);
//            Iterator<String> keys = jsonObject.keys();
//            while (keys.hasNext()) {
//                String key = keys.next();
//                String value = jsonObject.getString(key);
//                if (!VerifyUtil.isEmpty(key) && !VerifyUtil.isEmpty(value)) { //key value都不为空
//                    map.put(key, value);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
}
