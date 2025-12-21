package jp.co.isid.mos.bird.entry.mlentry.common;

import java.security.MessageDigest;
import java.security.SecureRandom;

import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

/**
 * マスターライセンス受験申込 定数クラス
 * @author Aspac
 *
 */
public class MlEntryCommon {
    
    /* VIEW_ID */
    public static final String VIEW_ID           = "BEN008";
    public static final String VIEW_ID_SELECT    = "BEN008V01";//マスターライセンス選択画面
    public static final String VIEW_ID_EDIT      = "BEN008V02";//マスターライセンスエントリー編集画面
    public static final String VIEW_ID_CONFIRM   = "BEN008V03";//マスターライセンスエントリー登録確認(完了)画面
    
    // 店検索
    public static final String VIEW_ID_MISESEARCH  = "BCO008V01";
    // スタッフ登録
    public static final String VIEW_ID_STAFFREGIST = "BSM004V02";
    // スタッフ選択
    public static final String VIEWID_STAFFSELECT = "BEN093V01";
    // VIEW_ID(操作エラー)
    public final static String operationErr_VIEW_ID = "operation.Err";
    
    /* 編集モード */
    //1：新規
    public final static String EDIT_MODE_INSERT = "1";
    //2：編集
    public final static String EDIT_MODE_UPDATE = "2";
    //3：削除
    public final static String EDIT_MODE_DELETE = "3";
    
    
    /* 会社コード */
    public final static String COMPANY_CD_MOS = "00";
    
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
    
    /* エントリーコード マスターライセンス */
    public static final String ENTRYCD_LICENSE = "10";

    /* 宛先区分 */
    public static final String ATESAKI_KBN_MOSIKOMI_SEKININ = "00";
    public static final String ATESAKI_KBN_RENRAKU          = "01";
    public static final String ATESAKI_KBN_TANTOU           = "02";
    public static final String ATESAKI_KBN_KEKKA_HOKOKUSAKI = "03";
    
    /* 削除フラグ */
    public static final String SAKUJO_FLG_ON = "1";
    public static final String SAKUJO_FLG_OFF= "0";
    
    /* ユーザータイプコード */
    public static final String USER_TYPE_CD_HONBU = "01";
    public static final String USER_TYPE_CD_ONER = "02";
    
    /* ライセンス総合結果 (BT32MLKR) */
    public static final String TOTAL_RESULT_FLG_MUKO      = "0";
    public static final String TOTAL_RESULT_FLG_HORYU     = "1";
    public static final String TOTAL_RESULT_FLG_FUGOUKAKU = "2";
    public static final String TOTAL_RESULT_FLG_HAKKOMATI = "3";
    public static final String TOTAL_RESULT_FLG_HAKKOZUMI = "4";
    public static final String TOTAL_RESULT_FLG_MIJYUKEN  = "9";
    
    /* 再エントリーフラグ (BT32MLKR) */
    public static final String REENTRY_FLG_DEF = "0";
    public static final String REENTRY_FLG_RE  = "1";
    
    /* ライセンス種別 (BT32MLKR) */
    public static final String LICENSE_KBN_IPPAN  = "01";
    public static final String LICENSE_KBN_JYOKYU = "02";
    
    /* 能力・筆記・面接 各チェックステータス (BT32MLKR) */
    public static final String SUB_BASE_RESULT_FLG_FUGOUKAKU  = "0";
    public static final String SUB_BASE_RESULT_FLG_GOUKAKU    = "1";
    public static final String SUB_BASE_RESULT_FLG_MENJYO     = "2";
    public static final String SUB_BASE_RESULT_FLG_JYUKENFUKA = "3";
    public static final String SUB_BASE_RESULT_FLG_MIJYUKEN   = "9";

    /* ライセンス失効フラグ (BT26UPJK) */
    public static final String LICENSE_EXPIRE_FLG_YUKO       = "0";
    public static final String LICENSE_EXPIRE_FLG_SIKKO      = "1";
    public static final String LICENSE_EXPIRE_FLG_HAKKOMATI  = "2";

    
    
    /* 面接を前回受験したか */
    public static final String BEFORE_SUB3_JYUKEN = "1";
    
    /* 能力・筆記・面接 各チェックステータス(BT23MLEJ) */
    public static final String CHK_STATE_JYUKEN       = "0";
    public static final String CHK_STATE_MENJO        = "1";
    public static final String CHK_STATE_JYUKEN_FUKA  = "2";
    public static final String CHK_STATE_MIJYUKEN     = "3";
    
    /* チェックステータス文言 */
    public static final String CHK_STATE_STR_JYUKEN       = "受験";
    public static final String CHK_STATE_STR_MENJO        = "免除";
    public static final String CHK_STATE_STR_JYUKEN_FUKA  = "受験不可";
    public static final String CHK_STATE_STR_MIJYUKEN     = "申込なし";
    
    
    /**
     * エントリーステータス(UIEntryState.STATES_FLG)
     * 0:未エントリー＋履歴がない
	 */
    public static final String LIST_STATES_FLG_NO_ENTRY_NO_RECORD  = "0";
    /**
     * エントリーステータス(UIEntryState.STATES_FLG)
     * 1:未エントリー＋履歴がある
	 */
    public static final String LIST_STATES_FLG_NO_ENTRY_RECORD     = "1";
    /**
     * エントリーステータス(UIEntryState.STATES_FLG)
     * 2:済エントリー＋履歴がない
     * ※空レコードは存在するが有効な過去履歴がない状態を含む。
     * ※BT32MLKRのREENTRY_FLGが "0"(初回エントリー)の状態
	 */
    public static final String LIST_STATES_FLG_ENTRY_NO_RECORD     = "2";
    /**
     * エントリーステータス(UIEntryState.STATES_FLG)
     * 3:済エントリー＋履歴がある
	 */
    public static final String LIST_STATES_FLG_ENTRY_RECORD        = "3";
    
    /**
     *  オーナー別エントリー状況（BT20ENON）
     */
    
    /* 参加フラグ  1:参加  "":不参加 */
    public static final String ENTRY_FLG_FUSANKA = "";
    public static final String ENTRY_FLG_SANKA   = "1";
    
    
    public static final String SHA1PRNG = "SHA1PRNG";
    public static final String MD5 = "MD5";
    
    /* チェックボックスの状態 */
    public static final String STATE_FLG_CHECKED_ON  = "1";
    public static final String STATE_FLG_CHECKED_OFF = "0";
    
    
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
