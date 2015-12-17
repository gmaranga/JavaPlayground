package com.wssecurity.example;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpsParameters;

public class HttpsPublisher {

	private static final int defaultPort = 3443;
	private static final int backlog = 12;
	private static final String keystore = "test.keystore";
	private IService serviceInstance;//service to publish
	
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
			Class serviceClass = Class.forName(serviceName);//use the Class.forName utility to load 
															//a service class from the file system
			serviceInstance = (IService) serviceClass.newInstance();
			
		}catch(Exception e){ throw new RuntimeException(e); }
		HttpsServer server = getHttpsServer(uri, defaultPort);
		
		if(server != null){
			server.createContext(uri);//sets the context for requests which in this example means
										//that a client must use the URI to hit the service
			System.out.println("Server listening on port " + defaultPort);
			server.start();//the start method is invoked on the HttpsServer instance, which
							//starts the server for an indefinitely long run.			
		}else{
			throw new RuntimeException("Cannot create server instance.");
		}
	}

	@SuppressWarnings("restriction")
	private HttpsServer getHttpsServer(String uri, int port) {
		HttpsServer server = null;
		try{
			InetSocketAddress inet = new InetSocketAddress(port);
			server = HttpsServer.create(inet, backlog);
			SSLContext sslCtx = SSLContext.getInstance("TLS");
			//Password for keystore
			char[] password = "qubits".toCharArray();
			KeyStore ks = KeyStore.getInstance("JKS");
			FileInputStream fis = new FileInputStream(keystore);
			ks.load(fis, password);
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, password);
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			tmf.init(ks);//same as keystore
			sslCtx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
			//Create SSL engine and configure HTTPS to use it.
			final SSLEngine eng = sslCtx.createSSLEngine();
			server.setHttpsConfigurator(new HttpsConfigurator(sslCtx){
				public void configure(HttpsParameters parms){
					parms.setCipherSuites(eng.getEnabledCipherSuites());
					parms.setProtocols(eng.getEnabledProtocols());
				}
			});
			server.setExecutor(null);//use default, hence single-threaded
			server.createContext(uri, new MyHttpsHandler(this.serviceInstance));
			
		}catch(Exception e){throw new RuntimeException(e);}
		
		return server;
	}

}
