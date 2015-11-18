//package com.restexample.jaxrs.application;
//
//import java.net.InetSocketAddress;
//
//import javax.ws.rs.ext.RuntimeDelegate;
//import javax.xml.ws.spi.http.HttpHandler;
//
//public class AdagesPublisher {
//
//	private static final int port = 9876;
//	private static final String uri = "/resourcesA/";
//	private static final String url = "http://localhost:" + port + uri;
//
//	public static void main(String[] args) {
//		new AdagesPublisher().publish();
//	}
//
//	private void publish() {
//		HttpServer server = getServer();
//		HttpHandler requestHandler = RuntimeDelegate.getInstance().createEndpoint(new RestfulAdage(),
//				HttpHandler.class);
//		server.createContext(uri, requestHandler);
//		server.start();
//		msg(server);
//	}
//
//	private HttpServer getServer() {
//		HttpServer server = null;
//		int backlog = 8;
//		try {
//			server = HttpServer.create(new InetSocketAddress("localhost", port), backlog);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return server;
//	}
//
//	private void msg(HttpServer server) {
//		String out = "Publishing RestfulAdage on " + url + ". Hit any key to stop.";
//		System.out.println(out);
//		try{
//			System.in.read();
//		}catch(Exception e){}
//		server.stop(0);
//	}
//
//}
