package com.wssecurity.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;

/**
 * 
 * @author Gmaranga 
 * 		   Underscore characters separate the parts of the cipher
 *         suite. Here is a summary of the parts: 
 *         
 *         TLS The Transport Layer Security, added to HTTP, yields HTTPS and thus accounts for the S in
 *         HTTPS. 
 *         ECDHE The acronym stands for Elliptic Curve Diffie-Hellman Key
 *         Exchange, which is the algorithm that governs the handshake. RSA This
 *         is the public key cryptography algorithm, named after Rivest, Shamir,
 *         and Adleman, the former MIT professors who designed it. 
 *         RSA is the most commonly used public key algorithm. It is used to encrypt the
 *         pre-master that is sent from the client to the server. Also, the
 *         public key on exchanged digital certificates comes from an
 *         RSA-generated key pair. 
 *         RC4_128 The stream cipher algorithm, which is used to encrypt and decrypt the bit traffic between client and
 *         server, has a key length of 128 bits. The R is for Rivest in RSA, and
 *         the C is for cipher. (Sometimes RC is said to be shorthand for Ron’s
 *         Code, as Rivest’s first name is Ron.) RC4 is the most commonly used
 *         stream cipher. RC4_128 is used to encrypt the data traffic once the
 *         handshake is completed. 
 *         SHA The certificate’s 160-bit identifying
 *         hash, also called its fingerprint, is generated with the Secure Hash
 *         Algorithm, officially officially known as a cryptographic hash
 *         function. There is a family of SHA algorithms but SHA-1, used here,
 *         is probably still the most widely used member of this family.
 */
public class GoogleClient {

	private static final String endpoint = "https://www.google.com:443/";

	public static void main(String[] args) {
		new GoogleClient().doIt();
	}

	private void doIt() {
		try {
			URL url = new URL(endpoint);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.connect();
			dumpDetails(conn);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void dumpDetails(HttpsURLConnection conn) {
		try {
			print("Status code: " + conn.getResponseCode());
			print("Cipher suite: " + conn.getCipherSuite());

			Certificate[] certs = conn.getServerCertificates();
			for (Certificate cert : certs) {
				print("");
				print("\tCert. type: " + cert.getType());
				print("\tHash code: " + cert.hashCode());
				print("\tAlgorithm: " + cert.getPublicKey().getAlgorithm());
				print("\tFormat: " + cert.getPublicKey().getFormat());
				print("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void print(Object s) {
		System.out.println(s);
	}

}
