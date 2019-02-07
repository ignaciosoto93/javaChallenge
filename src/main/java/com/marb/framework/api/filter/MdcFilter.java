package com.marb.framework.api.filter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MdcFilter extends OncePerRequestFilter {

	public static final String UOW = "uow";
	public static final String UOW_OVERRIDE = "uow-override";
	public static final String X_FORWARDED_FOR_HEADER = "X-Forwarded-For";

	@Value("${app.server.name:N/A}")
	private String serverName;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String uowOverride = extratExistingUOW(request);
		String uow = Optional.ofNullable(uowOverride).orElse(UUID.randomUUID().toString());
		MDC.put(UOW, uow);
		response.setHeader(UOW, uow);
		String ip = getUserIp(request);
		MDC.put("ip", ip);
		MDC.put("serverName", serverName);
		try {
			filterChain.doFilter(request, response);
		} finally {
			MDC.clear();
		}
	}

	private String extratExistingUOW(HttpServletRequest request) {
		String uow = Optional.ofNullable(request.getHeader(UOW_OVERRIDE)).orElse(request.getHeader(UOW));
		if (StringUtils.isBlank(uow)) {
			uow = request.getParameter(MdcFilter.UOW);
			if (StringUtils.isBlank(uow)) {
				Cookie c = org.springframework.web.util.WebUtils.getCookie(request, UOW);
				if (c != null) {
					uow = c.getValue();
				}
			}
		}
		return uow;
	}

	/**
	 * Returns the current user's IP
	 */
	private static String getUserIp(HttpServletRequest request) {
		String ip = request.getHeader(X_FORWARDED_FOR_HEADER);
		if (ip == null) {
			ip = request.getRemoteAddr();
		} else {
			ip = extractFirstIp(ip);
		}
		return StringUtils.isBlank(ip) ? null : ip;
	}

	private static String extractFirstIp(String ips) {
		String[] ipArr = ips.split(",");
		String ip = ipArr.length > 0 ? ipArr[0].trim() : null;
		return ip;
	}

}