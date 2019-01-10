package com.marb.framework.api.filter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MdfFilter extends OncePerRequestFilter {

	private static final String UOW = "uow";
	private static final String UOW_OVERRIDE = "uow-override";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String uow = String.format("uow:%s",
				Optional.ofNullable(request.getHeader(UOW_OVERRIDE)).orElse(UUID.randomUUID().toString()));
		MDC.put(UOW, uow);
		response.setHeader(UOW, uow);
		try {
			filterChain.doFilter(request, response);
		} finally {
			MDC.remove(UOW);
		}
	}
}