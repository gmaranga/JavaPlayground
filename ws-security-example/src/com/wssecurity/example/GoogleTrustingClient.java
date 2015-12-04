package com.wssecurity.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class GoogleTrustingClient {

	private static final String endpoint = "https://www.google.com:443/";

	public static void main(String[] args) {
		new GoogleTrustingClient().doIt();
	}

	private void doIt() {

		try {
			// Configure the HttpsURLConnection so that it does not check
			// certificates
			SSLContext sslContext = SSLContext.getInstance("TLS");
			TrustManager[] trustManagers = getTrustMgr();
			sslContext.init(null, // key manager
					trustManagers, // trust manager
					new SecureRandom());// random number generator
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

			URL url = new URL(endpoint);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.connect();
			dumpDetails(conn);

		} catch (MalformedURLException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void dumpDetails(HttpsURLConnection conn) {
		try {
			print("Status code: " + conn.getResponseCode());
			print("Cipher suite: " + conn.getCipherSuite());
			Certificate[] certs = conn.getServerCertificates();
			for (Certificate cert : certs) {
				print("\tCert. type: " + cert.getType());
				print("\tHash code: " + cert.hashCode());
				print("\tAlgorithm: " + cert.getPublicKey().getAlgorithm());
				print("\tFormat: " + cert.getPublicKey().getFormat());
				print("");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void print(String s) {
		System.out.println(s);
	}

	private TrustManager[] getTrustMgr() {
		//no method	throws a CertificateException, which means that the TrustManager effectively accepts
//		all certificates.
		
		TrustManager[] certs = new TrustManager[]{
			new X509TrustManager() {
				
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				
				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					
				}
				
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					
				}
			}		};
		return certs;
	}

}
