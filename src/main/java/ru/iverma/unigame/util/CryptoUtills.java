package ru.iverma.unigame.util;

import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class CryptoUtills {

	private CryptoUtills() {
	}

	public static String generateSaltHash(String pass) {
		if (pass == null) {
			throw new IllegalArgumentException("null password not allowed");
		} else {
			String salt = generateRandomHexString(16);
			String hash = generateHash(pass, salt);
			return salt + ":" + hash;
		}
	}

	public static String generateHash(String s, String salt) {
		String hash = sha256FromString(s);
		return sha256FromHexString(salt + hash);
	}

	public static String sha256FromString(String s) {
		return sha256(s.getBytes());
	}

	public static String sha256FromHexString(String s) {
		return sha256(Hex.decode(s));
	}

	public static String sha256(byte[] s) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			return Hex.toHexString(md.digest(s));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isCorrectPassword(String password, String saltHash) {
		String[] data = saltHash.split(":");
		var salt = data[0];
		var hash = data[1];
		return hash.equals(generateHash(password, salt));
	}

	public static String generateRandomHexString(int size) {
		SecureRandom random = new SecureRandom();
		byte[] saltByte = new byte[size];
		random.nextBytes(saltByte);
		return Hex.toHexString(saltByte);
	}
}
