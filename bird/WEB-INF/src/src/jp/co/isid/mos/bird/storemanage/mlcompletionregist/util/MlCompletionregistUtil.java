/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.util;


/**
 * マスタライセンス研修修了登録
 * 共通処理クラス
 * 
 * @author xkinu
 *
 */
public class MlCompletionregistUtil {
	
	/** 画面ID：BSM006 */
	public static final String SCREEN_ID = "BSM006";
	/** 分類コード:全クリア */
	public static final String BUNRUI_ALL_CLEAR = "01";
    /**
     * Nullチェック処理
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

}
