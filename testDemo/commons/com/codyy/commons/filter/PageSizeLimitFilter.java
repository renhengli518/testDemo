package com.codyy.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * ClassName: SearchPageAbleFilter
 * Function: 分页查询过滤器，防止恶意攻击
 * Reason: 
 * date: 2015年10月30日 上午9:21:03
 * 
 * @author wangqiqi 
 * @version  
 * @since JDK 1.7
 */
public class PageSizeLimitFilter implements Filter {

	
	private int pageSizeLimit = 100;//默认限制100
	
	private static final Log LOGGER = LogFactory.getLog(PageSizeLimitFilter.class);
	
	public PageSizeLimitFilter() {
		
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String startStr = request.getParameter("start");
		String endStr = request.getParameter("end");
		if(StringUtils.isNotBlank(startStr)&&StringUtils.isNotBlank(endStr)){
			int start = Integer.parseInt(startStr);
			int end = Integer.parseInt(endStr);
			int pageSize = end-start;//分页大小
			if(pageSize > getPageSizeLimit()){//分页大小超出
				String url = request.getServletPath();
				HttpServletResponse response = (HttpServletResponse)resp;
				LOGGER.error("page size is out of limit. url="+url+" the limit page size is "+getPageSizeLimit());
				response.getWriter().write("page size is out of limit!");
				return ;
			}
		}
		chain.doFilter(req, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String pageSizeLimitStr = fConfig.getInitParameter("pageSizeLimit");
		if(StringUtils.isNotBlank(pageSizeLimitStr)){
			setPageSizeLimit(Integer.parseInt(pageSizeLimitStr));
		}
	}

	public int getPageSizeLimit() {
		return pageSizeLimit;
	}

	public void setPageSizeLimit(int pageSizeLimit) {
		this.pageSizeLimit = pageSizeLimit;
	}

}
