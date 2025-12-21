/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportref.code;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * タブ定義クラス
 * 
 * 作成日:2010/10/25
 * @author xkinu
 *
 */
public class Tab {
	/** 売上 */
    public static final String KIN = "kin";

    /** 件数 */
    public static final String KEN = "ken";

    /** タブ配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{KIN, "売上"},
    	{KEN, "件数"}};
 
    /**
     * 外部からインスタンス化できない
     */
    private Tab() {
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
     * タブリストを取得する
     * @return	List　タブリスト
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
