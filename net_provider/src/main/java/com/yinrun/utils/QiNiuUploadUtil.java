package com.yinrun.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rs.PutPolicy;
import com.yinrun.bean.ResultModule;
import com.yinrun.interfaces.Power;

@Component
@RequestMapping("/qiniu")
public class QiNiuUploadUtil
{
    
    @Value("${QINIU_ACCESS_KEY}")
    private String QINIU_ACCESS_KEY;
    
    @Value("${QINIU_SECRET_KEY}")
    private String QINIU_SECRET_KEY;
    
    @Value("${BUKET_NAME}")
    private String BUKET_NAME;
    
    /**
     * 七牛云上传 获取上传token
     * @return 必须返回此类型 {"uptoken":"....."}
     * @author 罗熹林
     */
    @RequestMapping("/getUpToken")
    @ResponseBody
    @Power(noPower=true)
    public Object getUpToken()
    {
        Config.ACCESS_KEY = QINIU_ACCESS_KEY;
        Config.SECRET_KEY = QINIU_SECRET_KEY;
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        // 请确保该bucket已经存在
        String bucketName = BUKET_NAME;
        PutPolicy putPolicy = new PutPolicy(bucketName);
        String uptoken = "";
        try
        {
            uptoken = putPolicy.token(mac);
        }
        catch (AuthException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uptoken", uptoken);
        return ResultModule.success("查询成功", map);
    }
}
