package com.rc.pgapimodule.core.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacUtil {
	public static void main(String[] args) throws Exception {
			String resHmac = "w4OzySKeQqHaWD4Sy+rY3JrBE8PAh8s63HPGyo+pBkU=";
			String skey = "d73bf4b04fd876dcc779de8a146f6c9b";
			String amount = "1000";
			String okUrl = "http://localhost:18080/payment/callback/success";
			String tid = "ENSTRD20250808113654994650";
			String timeStamp = "20250808113653";
			String hmac = amount+okUrl+tid+timeStamp;

			System.out.println("###################### 시작 #######################");
			System.out.println(hmacDigest(skey, hmac));
			System.out.println(resHmac);
			System.out.println("###################### 종료 #######################");
		}

		public static String hmacDigest(String key, String msg) {
			String digest = null;
			byte[] bytes = null;
			String algorithm = "HmacSHA256";
			try {
				SecretKeySpec hmac_key = new SecretKeySpec((key).getBytes("UTF-8"), algorithm);
				Mac mac = Mac.getInstance(algorithm);
				mac.init(hmac_key);
				bytes = Base64.encodeBase64(mac.doFinal(msg.getBytes("UTF-8")));
				digest = new String(bytes);
			} catch (UnsupportedEncodingException e) {
			} catch (InvalidKeyException e) {
			} catch (NoSuchAlgorithmException e) {
			}
			return digest;
		}
}
