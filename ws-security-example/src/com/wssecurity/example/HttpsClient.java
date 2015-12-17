package com.wssecurity.example;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyStore;

import javax.management.RuntimeErrorException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

/**
 * Test client to use against the TestService published by HttpsPublisher
 * @author Gmaranga
 *
 */
public class HttpsClient {
	
	private static final String endpoint = "https://localhost:3443/test/";
	private static final String truststore = "test.keystore";

	public static void main(String[] args) {

		new HttpsClient().runTests();
	}

	private void runTests() {
		try{
			SSLContext sslCtx = SSLContext.getInstance("TLS");
			//passowrd for trustore (same as server keystore)
			char[] password = "qubits".toCharArray();
			KeyStore ks = KeyStore.getInstance("JKS");
			FileInputStream fis = new FileInputStream(truststore);
			ks.load(fis, password);
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			tmf.init(ks);//same as keystore
			sslCtx .init(null,//Not needed, not challenged
					tmf.getTrustManagers(),
					null);//use default: SecureRandom
			HttpsURLConnection.setDefaultSSLSocketFactory(sslCtx.getSocketFactory());
			URL url = new URL(endpoint);
			HttpsURLConnection conn = getConnection(url, "GET");
			getTest(conn);
			conn = getConnection(url, "POST");
			postTest(conn);
			conn = getConnection(url, "PUT");
			putTest(conn);
			conn = getConnection(url, "DELETE");
			deleteTest(conn);
			
			
			
		}catch(Exception e){throw new RuntimeException(e);}
	}

	private HttpsURLConnection getConnection(URL url, String verb) {
		
		try{
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod(verb);
			//Guard against "bad hostname" errors during handshake
			conn.setHostnameVerifier(new HostnameVerifier() {
				
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return hostname.equals("localhost");//for development
				}
			});
			
		}catch(Exception e){	throw new RuntimeException(e); }
			
		return null;
	}
	
	private void deleteTest(HttpsURLConnection conn) {
		try{
			
			conn.connect();
			readResponse("DELETE request: ", conn);
			conn.disconnect();
			
		}catch(Exception e){ throw new RuntimeException(e); }		
	}

	private void putTest(HttpsURLConnection conn) {
		try{
			
			conn.connect();
			writeBody(conn);
			readResponse("PUT request: ", conn);
			conn.disconnect();
			
		}catch(Exception e){ throw new RuntimeException(e); }		
	}

	private void postTest(HttpsURLConnection conn) {
		try{
			
			conn.connect();
			writeBody(conn);
			readResponse("POST request: ", conn);
			conn.disconnect();
			
		}catch(Exception e){ throw new RuntimeException(e); }		
	}

	private void writeBody(HttpsURLConnection conn) {
		try{
			OutputStream out = conn.getOutputStream();
			out.write("foo bar baz".getBytes());
			out.flush();
		}catch(Exception e){	throw new RuntimeException(e); }
	}

	private void getTest(HttpsURLConnection conn) {

		try{
			
			conn.connect();
			readResponse("GET request: ", conn);
			conn.disconnect();
			
		}catch(Exception e){ throw new RuntimeException(e); }
	}

	private void readResponse(String msg, HttpsURLConnection conn) {
		try{
			byte[] buffer = new byte[512];//plenty for testing
			InputStream in = conn.getInputStream();
			in.read(buffer);
			System.out.println(msg + buffer.toString());
		}catch(Exception e){	throw new RuntimeException(e); }
	}


}
