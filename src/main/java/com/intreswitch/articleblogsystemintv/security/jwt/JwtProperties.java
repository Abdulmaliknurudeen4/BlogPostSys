package com.intreswitch.articleblogsystemintv.security.jwt;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class JwtProperties {
	public static String SECRET = "";
	public static final int EXPIRATION_TIME = 864000000;
	public static final String PREFIX= "Bearer ";
	public static final String HEADER_STRING="Authorization";

	static {
		String seed = "my_secret_key_seed";

		// Generate a SHA-512 hash of the seed string
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] hash = digest.digest(seed.getBytes(StandardCharsets.UTF_8));

		// Extract the first 64 bytes of the hash as the key
		byte[] keyBytes = new byte[64];
		System.arraycopy(hash, 0, keyBytes, 0, 64);

		// Create a SecretKey object from the key bytes
		SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA512");

		// Encode the key in base64
		SECRET = Base64.getEncoder().encodeToString(key.getEncoded());

	}




}
