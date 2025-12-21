package jp.co.isid.mos.bird.framework.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

public class MakeSessionKey {
    
    // セッションキー作成 /////////////////////////////////////////////
    public static final String SHA1PRNG = "SHA1PRNG";
    public static final String MD5 = "MD5";
    // VIEW_ID(操作エラー)
    public final String operationErr_VIEW_ID = "operation.Err";

    /**
     *  セッションキー作成処理 
     */
    public String _makeSessionKey() {
        byte[] bytes = new byte[256];
        byte[] key;
        try {
            SecureRandom random = SecureRandom.getInstance(SHA1PRNG);
            MessageDigest md = MessageDigest.getInstance(MD5);
            random.nextBytes(bytes);
            md.update(bytes);
            key = md.digest();
        }
        catch (Exception ex) {
            throw new FtlSystemException("セッションキー作成失敗");
        }
        return asHex(key);
    }
    
    /* 16進数変換 */
    private static String asHex(byte bytes[]) {
        StringBuffer strbuf = new StringBuffer(bytes.length * 2);
        for (int index = 0; index < bytes.length; index++) {
            int bt = bytes[index] & 0xff;
            if (bt < 0x10) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString(bt, 16));
        }
        return strbuf.toString().toUpperCase();
    }
    
    /**
     * sessionKey有効チェック
     * @param param 画面保持セッションキー
     * @param sessionKey セッションキー
     * @return
     */
    public boolean isValidSessionKey(String param, String sessionKey) {
        if (param != null && sessionKey != null && param.equals(sessionKey)) {
            return true;
        } else {
            return false;
        }
    }

}
