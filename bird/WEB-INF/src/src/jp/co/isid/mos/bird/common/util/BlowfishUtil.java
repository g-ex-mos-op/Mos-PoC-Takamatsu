package jp.co.isid.mos.bird.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import jp.co.isid.mos.bird.common.entity.UIOutLink;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BlowfishUtil {

	public static final String ENCRYPT_KEY = "zfnvFFwXjQGabs8s";

	public static final String TRANSFORMATION = "Blowfish/ECB/PKCS5Padding";

	public static final String BLOWFISH = "Blowfish";

	// minisite用の共通キー
	public static final String MINISITE_DECRYPT_KEY = "Qcui9Nc6J4qzHL8v";

	/**
	 *
	 * @param key
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String key, String text) throws Exception {
		SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), BLOWFISH);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, sksSpec);
		byte[] encrypted = cipher.doFinal(text.getBytes());
		String encStr = new String(encrypted, "iso-8859-1");
		byte[] encodedByte2 = encStr.getBytes("iso-8859-1");
		return URLEncoder.encode((new BASE64Encoder().encode(encodedByte2)));
	}


	public static String decrypt(String key, String text) throws Exception {
		String urlDecoder = URLDecoder.decode(text);
		byte[] base64Decoder = new BASE64Decoder().decodeBuffer(urlDecoder);
		SecretKeySpec skeSpect = new SecretKeySpec(key.getBytes(), BLOWFISH);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, skeSpect);
		byte[] decrypted = cipher.doFinal(base64Decoder);
		return new String(decrypted,"iso-8859-1");
	}

	/**
	 * 暗号化する
	 * @param key  秘密鍵
	 * @param text 文字列
	 * @return 暗号化した文字列
	 */
	public static String encryptUrl(String key, String text) throws Exception {

		//暗号化設定
		SecretKeySpec skey = new SecretKeySpec(key.getBytes(), BLOWFISH);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, skey);

		//暗号化する
		byte[] encrypted = cipher.doFinal(text.getBytes());

		//BASE64エンコード
		byte[] enc_encode = Base64.getUrlEncoder().encode(encrypted);

		return new String(enc_encode);

	}

	/**
	 * 暗号化する
	 * @param key  秘密鍵
	 * @param text 文字列
	 * @return 暗号化した文字列
	 */
	public static String encryptNotUrl(String key, String text) throws Exception {

		//暗号化設定
		SecretKeySpec skey = new SecretKeySpec(key.getBytes(), BLOWFISH);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, skey);

		//暗号化する
		byte[] encrypted = cipher.doFinal(text.getBytes());

		//BASE64エンコード
		byte[] enc_encode = Base64.getEncoder().encode(encrypted);

		return new String(enc_encode);

	}

	/**
	 * 復号化する
	 * @param key  秘密鍵
	 * @param text 暗号化した文字列
	 * @return 復号化した文字列
	 */
	public static String decryptUrl(String key, String text) throws Exception {

		//BASE64デコード
		byte[] enc_decode = Base64.getUrlDecoder().decode(text.getBytes());

		//復号化設定
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		Key skey = new SecretKeySpec(key.getBytes(), BLOWFISH);
		cipher.init(Cipher.DECRYPT_MODE, skey);

		//復号化する
		byte[] decrypted = cipher.doFinal(enc_decode);

		return new String(decrypted);

	}

	/**
	 * 復号化する
	 * @param key  秘密鍵
	 * @param text 暗号化した文字列
	 * @return 復号化した文字列
	 */
	public static String decryptNotUrl(String key, String text) throws Exception {

		//BASE64デコード
		byte[] enc_decode = Base64.getDecoder().decode(text.getBytes());

		//復号化設定
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		Key skey = new SecretKeySpec(key.getBytes(), BLOWFISH);
		cipher.init(Cipher.DECRYPT_MODE, skey);

		//復号化する
		byte[] decrypted = cipher.doFinal(enc_decode);

		return new String(decrypted);

	}

	/**
	 * ミニサイトへアクセスため、URLのパラメータを暗号化する
	 * @param userId
	 * @return
	 */
	public static String setEncryptMiniSiteUrl(String userId) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String param = "";

		try {
			String encryptStr = encrypt(MINISITE_DECRYPT_KEY, userId + format.format(date));
			param = "?mos_login_key=" + encryptStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return param;
	}

	/**
	 * 暗号化した文字列
	 * @param userId
	 * @param paramUserId
	 * @return
	 */
// modify 2018/04/27 USI蔡 begin
//	public static String setEncrypt(String userId, String dougaCd) {
	public static String setEncrypt(String userId, String dougaCd, String userSakuseiFlg, String onerCd) {
// modify 2018/04/27 USI蔡 end
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String param = "";
		String encryptUserId;
		try {
			encryptUserId = encrypt(ENCRYPT_KEY, userId.trim());
			String encryptStr = encrypt(ENCRYPT_KEY, "mos@jstream");
			String encryptGroupId = encrypt(ENCRYPT_KEY, dougaCd.trim());
			String encryptCurrentTime = encrypt(ENCRYPT_KEY, format.format(date));
// modify 2018/04/27 USI蔡 begin
//			param = "?k=" + encryptStr + "&u=" + encryptUserId + "&g=" + encryptGroupId + "&t=" + encryptCurrentTime;
			String encryptUserSakuseiFlg = encrypt(ENCRYPT_KEY, userSakuseiFlg.trim());
			String encryptOnerCd = encrypt(ENCRYPT_KEY, onerCd.trim());
			param = "?k=" + encryptStr + "&u=" + encryptUserId + "&g=" + encryptGroupId + "&t=" + encryptCurrentTime
					+ "&c=" + encryptUserSakuseiFlg + "&o=" + encryptOnerCd;
// modify 2018/04/27 USI蔡 end
		} catch (Exception e) {
			e.printStackTrace();
		}
		return param;
	}

	public static void test(UIOutLink outLink) throws Exception {
		if ("25".equals(outLink.getSubMenuId())) {
			String url = outLink.getParam();
			String str = url.substring(3, url.lastIndexOf("&u="));
			String userId = url.substring(str.length() + 6, url.lastIndexOf("&g="));
			String groupId = url.substring(str.length() + userId.length() + 9, url.lastIndexOf("&t="));
			String currentTime = url.substring(str.length() + userId.length() + groupId.length() + 12, url.length());
			String decryptStr = decrypt(ENCRYPT_KEY, str);
			String decryptUserId = decrypt(ENCRYPT_KEY, userId);
			String decryptGroupId = decrypt(ENCRYPT_KEY, groupId);
			String decryptCurrentTime = decrypt(ENCRYPT_KEY, currentTime);
		}
	}
}
