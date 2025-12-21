package jp.co.isid.mos.bird.analysis.sibuaverage.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.AbstShukeiKbn;

/**
 * 集計区分コード定数クラス
 * 
 * 作成日:2012/10/31
 * @author xkinu
 *
 */
public class ShukeiKbn extends AbstShukeiKbn {

    /** 対象店舗配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{IN_RC, LABEL_IN_RC},{OUT_RC, LABEL_OUT_RC}};
 
    /**
     * 外部からインスタンス化できない
     */
    private ShukeiKbn() {
    	super();
    }  

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
    public static List getUIListPullDownList() {
    	return getUIListPullDownList(CODE_TABLE);
    }
}
