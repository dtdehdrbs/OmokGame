package omok;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptMD5 {
	public final static String encryptMD5(String str){
		byte[] hash = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			try {
				md5.update(str.getBytes("UTF-8"));
				hash = md5.digest();
			} catch (UnsupportedEncodingException e) {
			}
			
		} catch (NoSuchAlgorithmException e) {
		}
		
		StringBuilder hexMD5hash = new StringBuilder();
		 for (byte b : hash) {
		   String hexString = String.format("%02x", b);
		   hexMD5hash.append(hexString);
		}
		return new String(hexMD5hash);
	}

}
