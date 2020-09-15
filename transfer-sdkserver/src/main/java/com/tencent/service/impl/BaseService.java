package com.tencent.service.impl;

import com.tencent.constants.ServiceCodeConstants;

import java.util.HashMap;
import java.util.Map;

public class BaseService {
    /**
     * 获取失败的返回内容
     * @date 2019年12月2日
     * @author Lvshiyang
     * @param msg
     */
    protected Map<String, Object> getFailRtn(String msg) {
        Map<String, Object> rtn = new HashMap<String, Object>(16);
        rtn.put("code", ServiceCodeConstants.FAIL_RTN);
        rtn.put("msg", msg);
        rtn.put("data", null);
        return rtn;
    }

    /**
     * 获取成功返回
     * @date 2019年12月2日
     * @author Lvshiyang
     * @param data
     */
    protected Map<String, Object> getSuccessRtn(Object data) {
        Map<String, Object> rtn = new HashMap<String, Object>(16);
        rtn.put("code", ServiceCodeConstants.SUCCESS_RTN);
        rtn.put("msg", "");
        rtn.put("data", data);
        return rtn;
    }

    /**
     * 获取成功返回
     * @date 2019年12月2日
     * @author Lvshiyang
     * @param data
     */
    protected Map<String, Object> getSuccessRtn(Object data, String msg) {
        Map<String, Object> rtn = new HashMap<String, Object>(16);
        rtn.put("code", ServiceCodeConstants.SUCCESS_RTN);
        rtn.put("msg", msg);
        rtn.put("data", data);
        return rtn;
    }

    /**
     * 具体业务状态码返回体封装方法
     * @return
     * @date 2019年12月2日
     * @author Lvshiyang
     */
    protected Map<String, Object> getRtnCode(Object data, String msg, Integer code) {
        Map<String, Object> rtn = new HashMap<String, Object>(16);
        rtn.put("code", code);
        rtn.put("msg", msg);
        rtn.put("data", data);
        return rtn;
    }
}
