package web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

public class Log4jFillter implements Filter {

	private final static double DEFAULT_USERID = Math.random() * 100000.0;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session == null) {
			MDC.put("userId", "-1");
			MDC.put("userName", "未登录用户");
		} else {
			OperatorBean operatorBean = (OperatorBean) session
					.getAttribute(SysConfigSingle.getInstance().SYSUSERINFO);
			if (operatorBean == null) {
				MDC.put("userId", "-1");
				MDC.put("userName", "未登录用户");
			} else {
				MDC.put("userId", operatorBean.getUser().getUserid());
				MDC.put("userName", operatorBean.getUser().getUsername());
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig Config) throws ServletException {

	}

	public void destroy() {

	}
}
