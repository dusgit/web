package com.yinrun.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yinrun.interfaces.Power;

import tk.mybatis.mapper.util.StringUtil;

public class SysPowerInterceptor implements HandlerInterceptor
{
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Map<String, Object>> redis;
    
    /*
     * 在请求已经返回之后执行(non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.
     * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception
    {
    }

    /*
     * 执行目标方法之后执行(non-Javadoc)
     * 
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.
     * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception
    {
    }

    /*
     * 在执行目标方法之前执行(non-Javadoc)
     * 
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.
     * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的Power注解
        Power power = method.getAnnotation(Power.class);
        if (power == null)
        {
            return writeFalseBack(response);// 所有方法必须添加权限开关
        }
        // 需要显示的说明不需要权限
        if (power.noPower())
        {
            return true;
        }
        if (power.value() != null && power.value().length > 0)
        {
            String token = request.getHeader("token");
            if (StringUtil.isEmpty(token))
            {
                return writeFalseBack(response);
            }
            Map<String, Object> results = redis.get(token);
            if (results == null)
            {
                return writeFalseBack(response);
            }
            Map<String, String> powerMap = (Map<String, String>) results.get("powerMap");
            if (powerMap == null)
            {
                return writeFalseBack(response);
            }
            // 如果权限配置不为空, 则取出配置值
            String[] powerArray = power.value();
            for (String item : powerArray)
            {
                if (powerMap.containsKey(item))
                {
                    return true;
                }
            }
        }
        return writeFalseBack(response);
    }

    private boolean writeFalseBack(HttpServletResponse response)
    {
        JSONObject obj = new JSONObject();
        obj.put("status", "false");
        obj.put("message", "server:no power");
        try
        {
            response.getWriter().write(obj.toJSONString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
