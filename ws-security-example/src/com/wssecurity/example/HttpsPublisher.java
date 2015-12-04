package com.wssecurity.example;

import com.sun.net.httpserver.HttpsServer;

public class HttpsPublisher {

	private static final int defaultPort = 3443;
	private static final int backlog = 12;
	private static final String keystore = "test.keystore";
	private IService serviceInstance;
	
	public static void main(String[] args) {

		if(args.length < 2){
			System.err.println("Usage: java httpsPublisher <service> <path>");
			return;
		}
		
		//% java HttpsPublisher myService.Service /service
		new HttpsPublisher().init(args[0], //service name
									args[1]);//URI
		
	}
	
	private void init(String serviceName, String uri){
		
		try{
			Class serviceClass = Class.forName(serviceName);
			serviceInstance = (IService) serviceClass.newInstance();
			
		}catch(Exception e){ throw new RuntimeException(e); }
		HttpsServer server = getHttpsServer(uri, defaultPort);
		
		if(server != null){
			server.createContext(uri);
			System.out.println("Server listening on port " + defaultPort);
		}
	}

	private HttpsServer getHttpsServer(String uri, int defaultport2) {
		// TODO Auto-generated method stub
		return null;
	}

}
