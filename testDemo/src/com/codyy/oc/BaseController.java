package com.codyy.oc;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import com.codyy.commons.CommonsConstant;
import com.codyy.commons.sso.SessionUser;
import com.codyy.oc.questionlib.util.ObjectUtil;

/**
 * 基础controller，实现通用方法
 */
@Controller
public class BaseController {

	/**
	 * 获取session用户
	 * 
	 * @author Gwang
	 * @param request
	 * @return
	 */
	protected SessionUser getSessionUser(HttpServletRequest request) {
		Object user = request.getSession().getAttribute(CommonsConstant.SESSION_USER);
		return user == null ? null : (SessionUser) user;
	}

	/**
	 * 获取session用户ID
	 * 
	 * @author Gwang
	 * @param request
	 * @return
	 */
	protected String getSessionUserId(HttpServletRequest request) {
		Object user = request.getSession().getAttribute(CommonsConstant.SESSION_USER);
		return user == null ? null : ((SessionUser) user).getUserId();
	}

	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}*/
	
	/**
	 * ajax返回调用
	 * @param response
	 * @param object
	 */
	protected void callBack(HttpServletResponse response, Object data) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
		    response.setHeader("content-type", "text/html;charset=UTF-8");
		    response.setContentType("text/html;charset=UTF-8");
			response.setDateHeader("Expires", 0);
			PrintWriter out = response.getWriter();
			if (data instanceof String) {
				out.print(data.toString());
			} else {
				out.print(ObjectUtil.obj2Json(data));
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
