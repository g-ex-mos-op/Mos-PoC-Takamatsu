/**
 * 
 */
package jp.co.isid.mos.bird.commonform.miseonersearch.util;


/**
 * 店・オーナー検索画面定数処理クラス
 * 
 * 作成日:2008/11/17
 * @author xkinu
 *
 */
public class MiseOnerSearchUtil {
	/** 画面ID */
	public static final String SCREEN_ID = "BCO014";
    /** VIEW_ID：初期画面 */
    public static final String VIEW_ID = SCREEN_ID+"V01";

    public static String getBunruiName(String bunruiKbn) {
    	if("1".equals(bunruiKbn)) {
    		return "オーナー";
    	}
    	else if("2".equals(bunruiKbn)){
    		return "店";
    	}
    	else if("3".equals(bunruiKbn)){
    		return "店・オーナー";
    	}
    	return "";
    }
}
