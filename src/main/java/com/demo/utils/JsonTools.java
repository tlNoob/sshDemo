

package com.demo.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonTools {
    public static JSONObject ToGridJson(Object obj, String totalRows) {
        /*StringBuilder str = new StringBuilder();
        String json = "";
        *//*str.append("{\"pager.pageNo\":" + pageNo + ",\"pager.totalRows\":" + totalRows
                + ",\"rows\":");*//*
        str.append("{\"code\":"+0+",\"msg\":"+"\"\""+",\"count\":"+totalRows+",\"data\":");
        json=JSON.toJSON(obj).toString();
        str.append(json);
        str.append("}");*/
       JSONObject data=new JSONObject(); // 使用JSONObject返回页面
        //需要返回的数据
        data.put("code", 0);//照着写
        data.put("msg", "");//照着写
        data.put("count", totalRows);//总条数 数据库查
        data.put("data", obj);//你查出来的list这里put到前台去

        return data;
    }

}
