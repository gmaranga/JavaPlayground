package com.wsexample.sib;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.wsexample.sei.EchoService;

@WebService(endpointInterface="com.wsexample.sei.EchoService")
public class EchoServiceImpl implements EchoService {

	
	/*
	 * The WebServiceContext, in turn, can be used to access the MessageContext , which provides information about
	 * the transport level.
	 */
	@Resource
	WebServiceContext wctx;
	
	@Override
	public String echo(String in) {
		
		String out = "Echoing: " + in;
		
		//Hit the transport level to extract the HTTP headers
		MessageContext mctx = wctx.getMessageContext();
		Map requestHeaders = (Map)mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
		dump(requestHeaders,"");
		
		return out;
	}

	private void dump(Map requestHeaders, String indent) {
		
		Set keys = requestHeaders.keySet();
		for(Object key : keys){
			System.out.println(indent + key + ":" + requestHeaders.get(key));
			if(requestHeaders.get(key) instanceof Map){
				dump((Map) requestHeaders.get(key), indent += " ");
			}
		}
	}

}
