package com.example.gateway;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

//forward our authentication token after login. This will propagate authentication to any backing service after login
@Component
public class SessionSavingZuulPreFilter extends ZuulFilter {

	@Autowired
	private SessionRepository repository;
	
	@Override
	public Object run() {

		RequestContext context = RequestContext.getCurrentContext();
		HttpSession httpSession = context.getRequest().getSession();
		Session session = repository.getSession(httpSession.getId());
		
		context.addZuulRequestHeader("Cookie", "SESSION=" + httpSession.getId());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
