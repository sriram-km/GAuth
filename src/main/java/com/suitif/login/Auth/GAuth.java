package com.suitif.login.Auth;


import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;


import de.taimos.totp.TOTP;

public class GAuth {
	public static String generateSecretKey() {
	    SecureRandom random = new SecureRandom();
	    byte[] bytes = new byte[20];
	    random.nextBytes(bytes);
	    Base32 base32 = new Base32();
	    return base32.encodeToString(bytes);
	}
	
	public static String getTOTPCode(String secretKey) {
	    Base32 base32 = new Base32();
	    byte[] bytes = base32.decode(secretKey);
	    String hexKey = Hex.encodeHexString(bytes);
	    return TOTP.getOTP(hexKey);
	}

	public static void authendicate() {
		String secretKey = "7YKH6WAPZOLP56UOCE3SD4HKZMMTSD7R";
		System.out.println(secretKey);
		String lastCode = null;
		while (true) {
		    String code = getTOTPCode(secretKey);
		    if (!code.equals(lastCode)) {
		        System.out.println(code);
		    }
		    lastCode = code;
		    try {
		        Thread.sleep(1000);
		    } catch (InterruptedException e) {};
		}
	}
	
	/*
	 * public static String getGoogleAuthenticatorBarCode(String secretKey, String
	 * account, String issuer) { try { return "otpauth://totp/" +
	 * URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20") +
	 * "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20") +
	 * "&issuer=" + URLEncoder.encode(issuer, "UTF-8").replace("+", "%20"); } catch
	 * (UnsupportedEncodingException e) { throw new IllegalStateException(e); } }
	 * 
	 * public static void createQRCode(String barCodeData, String filePath, int
	 * height, int width) throws WriterException, IOException { BitMatrix matrix =
	 * new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE, width,
	 * height); try (FileOutputStream out = new FileOutputStream(filePath)) {
	 * MatrixToImageWriter.writeToStream(matrix, "png", out); } }
	 */
	
	
	
	/*
	 * public static void main(String args[]) throws WriterException, IOException {
	 * String secretKey = "7YKH6WAPZOLP56UOCE3SD4HKZMMTSD7R"; String email =
	 * "test@gmail.com"; String companyName = "Suitify"; String barCodeUrl =
	 * getGoogleAuthenticatorBarCode(secretKey, email, companyName);
	 * createQRCode(barCodeUrl,"/home/local/ZOHOCORP/ram-zsch976/qr.png",100,100);
	 * System.out.println(barCodeUrl); authendicate(); }
	 */
}
