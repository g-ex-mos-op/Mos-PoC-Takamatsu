/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.AbstractCodeUtil;

/**
 * 店舗衛生結果タブ定数クラス
 * 作成日:2012/12/07
 * @author xkinu
 *
 */
public class Tab1st extends AbstractCodeUtil {
	/** タブ1：店舗衛生監査 */
    public static final String TAB_1 = "2se";
    /** タブ2：店舗衛生情報 */
    public static final String TAB_2 = "3sh";
	/** タブ3：モスプレート */
    public static final String TAB_3 = "mosp";
    /** タブ4：訪店指導 */
    public static final String TAB_4 = "4vs";

    /** コード配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{TAB_1, "店舗衛生監査"}
        ,{TAB_2, "店舗衛生情報"}
        ,{TAB_3, "モスプレート"}
        ,{TAB_4, "訪店指導"}
       };
    /**
     * コードの名称を取得する
     * @param	code	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
    	return getName(code, CODE_TABLE);    
    }
    /**
     * プルダウンリスト取得処理
     * @return
     */
    public static List getUIList() {
    	return getUIListPullDownList(CODE_TABLE);
    }
}
