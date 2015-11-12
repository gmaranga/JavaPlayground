package com.wsexample.client;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;

import com.wsexample.sei.EchoService;

public class EchoClient {

	private static final String defaultUrl = "http://localhost:8080/echo?wsdl";

	public static void main(String[] args) throws Exception {
		QName qname = new QName("http://sib.wsexample.com/", "EchoServiceImplService");
		Service service = Service.create(new URL(defaultUrl), qname);
		EchoService port = service.getPort(EchoService.class);
		//The binding provider gives access to the transport level.
		Map<String, Object> requestContext = ((BindingProvider) port).getRequestContext();

		Map<String, Object> myHeaders = new HashMap<>();
		myHeaders.put("Accept-Encoding", Collections.singletonList("gzip"));
		myHeaders.put("Greeting", Collections.singletonList("Hello world!"));
		requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, myHeaders);

		String response = port.echo("Have a nice day :)");
		Map<String, Object> responseContext = ((BindingProvider) port).getResponseContext();
		dump(responseContext, "");
		
		System.out.println(response);

	}

	private static void dump(Map requestHeaders, String indent) {

		Set keys = requestHeaders.keySet();
		for (Object key : keys) {
			System.out.println(indent + key + ":" + requestHeaders.get(key));
			if (requestHeaders.get(key) instanceof Map) {
				dump((Map) requestHeaders.get(key), indent += " ");
			}
		}
	}

}
