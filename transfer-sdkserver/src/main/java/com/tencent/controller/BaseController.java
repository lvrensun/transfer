package com.tencent.controller;

import com.tencent.constants.ServiceCodeConstants;
import com.tencent.wework.Finance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Title:BaseController </p>
 * <p>Company: 金阖科技 </p>
 * @author Lvshiyang
 * @date 2020-08-18 15:48:59
 */
public class BaseController {

	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);
	@Autowired
    HttpServletRequest request;

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
	protected Map<String, Object> callBackResultMap(List list) {
		Map<String, Object> rtn = new HashMap<String, Object>(16);
		rtn.put("code", "0");
		rtn.put("data", list);
		return rtn;
	}

	/**
	 * 获取当前请求地址
	 * @return
	 */
	protected String getReqUrl() {
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		int port = request.getLocalPort();
		String requestURI = request.getRequestURI();
		return scheme+"://"+serverName+":"+port+requestURI;
	}
	/**
	 * 获取当前请求接口
	 * @return
	 */
	protected String getAPI() {
		return request.getRequestURI();
	}

}
