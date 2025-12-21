/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportref.code;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.AbstShukeiKbn;

/**
 * 対象条件定義クラス
 * 
 * 作成日:2010/10/25
 * @author xkinu
 *
 */
public class TaishoJoken extends AbstShukeiKbn {
	/** 直営・○○を含まない */
    public static final String SIBU_CD = OUT_RC;

    /** 直営・○○を含む */
    public static final String AREA_DAI = IN_RC;

    /** 対象店舗配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{SIBU_CD, LABEL_OUT_RC},
    	{AREA_DAI, LABEL_IN_RC}};
 
    /**
     * 外部からインスタンス化できない
     */
    private TaishoJoken() {
    	super();
    }  

    /**
     * コードの名称を取得する
     * @param	code	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
		for(int i = 0; i < CODE_TABLE.length; i++) {
			if(CODE_TABLE[i][0].equals(code)) {
				return CODE_TABLE[i][1];
			}			
		}		
		return  "";
    }

    /**
     * 対象条件リストを取得する
     * @return	List　対象条件リスト
     */
    public static List getPullDownList() {
		List pullDownList = new ArrayList();
		for(int i = 0; i < CODE_TABLE.length; i++) {
			SelectItem item = new SelectItem(CODE_TABLE[i][0], CODE_TABLE[i][1]);
			pullDownList.add(item);
		}
		return pullDownList;
    }
}
