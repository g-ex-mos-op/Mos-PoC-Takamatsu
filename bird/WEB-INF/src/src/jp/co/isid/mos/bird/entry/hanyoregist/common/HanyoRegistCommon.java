package jp.co.isid.mos.bird.entry.hanyoregist.common;

import java.security.MessageDigest;
import java.security.SecureRandom;

import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

/**
 * 汎用研修マスタ登録 定数クラス
 * @author xnkusama
 *
 */
public class HanyoRegistCommon {
    
    /* VIEW_ID */
    public final static String VIEW_ID            = "BEN004";
    public final static String SELECT_VIEW_ID     = "BEN004V01";
    public final static String EDIT_VIEW_ID       = "BEN004V02";
    public final static String CONFIRM_VIEW_ID    = "BEN004V03";
    // VIEW_ID(操作エラー)
    public final static String operationErr_VIEW_ID = "operation.Err";
    
    /* 編集モード */
    //1：新規
    public final static String EDIT_MODE_INSERT = "1";
    //2：編集
    public final static String EDIT_MODE_UPDATE = "2";
    //3：削除
    public final static String EDIT_MODE_DELETE = "3";
    
    
    
    /* 日付区分 */
    //01：開催
    public static  final String DAY_KBN_KAISAI      = "01";
    //02：その他
    public static  final String DAY_KBN_SONOTA      = "02";
    //03：表示
    public static  final String DAY_KBN_HYOJI       = "03";
    //04：登録
    public static  final String DAY_KBN_TOROKU      = "04";
    //05：結果表示
    public static  final String DAY_KBN_KEKKA_HYOJI = "05";
    //06：結果登録
    public static  final String DAY_KBN_KEKKA_TOROKU = "06";
    
    /* ユーザータイプコード */
    //01:本部
    public static final String USERTYPE_CD_HONBU = "01";
    //02:オーナー
    public static final String USERTYPE_CD_ONER  = "02";
    //99:管理用
    public static final String USERTYPE_CD_KANRI = "99";
    

    /* 削除フラグ */
    public static final String SAKUJO_FLG_ON = "1";
    public static final String SAKUJO_FLG_OFF= "0";
    
    public static final String SHA1PRNG = "SHA1PRNG";
    public static final String MD5 = "MD5";
    /*汎用研修マスタコード 30:更新研修 2007/01/05 add inazawa*/
    public static final String KOUSIN_KENSHU = "30";

    /**
     *  セッションキー作成処理 
     */
    public static String makeSessionKey() {
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
    public static boolean isValidSessionKey(String param, String sessionKey) {
        if (param != null && sessionKey != null && param.equals(sessionKey)) {
            return true;
        } else {
            return false;
        }
    }
}
