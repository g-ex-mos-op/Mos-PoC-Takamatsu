/**
 * 
 */
package jp.co.isid.mos.bird.commonform.menusearch.util;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.commonform.common.util.IndexSearchUtil;

/**
 * メニュー選択画面定数処理クラス
 * 
 * 作成日:2008/08/20
 * @author xkinu
 *
 */
public class MenuSearchUtil {
	/** 画面ID */
	public static final String SCREEN_ID = "BCO012";
    /** VIEW_ID：初期画面 */
    public static final String VIEW_ID = SCREEN_ID+"V01";
    /** アクション種類:非稼動*/
    public static final int ACTION_KIND_NULL = -1;
    /** アクション種類:初期化*/
    public static final int ACTION_KIND_INIT = 0;
    /** アクション種類:選択済み*/
    public static final int ACTION_KIND_SELECT = 1;
    /** アクション種類:戻り*/
    public static final int ACTION_KIND_CANCEL = 2;
    /** アクション種類:稼動中*/
    public static final int ACTION_KIND_EXEC = 9;
    /** LIKE検索位置:から始まる*/
    public static final String LIKE_POINT_LEFT = "LEFT";
    /** LIKE検索位置:を含む*/
    public static final String LIKE_POINT_MIDDLE = "MIDDLE";
    /** LIKE検索位置:で終わる*/
    public static final String LIKE_POINT_RIGHT = "RIGHT";
    /** アクション種類:稼動中*/
    public static final String SORT_TYPE_CODE = "CODE";
    /** アクション種類:稼動中*/
    public static final String SORT_TYPE_NAME = "NAME";
    /** 選択モード */
    public static final int SELECT_MODE_SINGLE = 0;
    public static final int SELECT_MODE_MULTI = 1;
    
    /**
     * @return
     */
    public static List getListNum() {
    	return MenuSearchUtil.recreateSearchList(IndexSearchUtil.getListNum(false, true));
    }
    public static List getListKana() {
    	return MenuSearchUtil.recreateSearchList(IndexSearchUtil.getListKana(true, false));
    }
    public static List getListRoma() {
    	return MenuSearchUtil.recreateSearchList(IndexSearchUtil.getListRoma(false, true));
    }
    public static List getListKigo() {
    	return MenuSearchUtil.recreateSearchList(IndexSearchUtil.getListKigo(false, true));
    }
    /**
     * 
     * @param list
     * @return
     */
    private static List recreateSearchList(List list) {
    	List listSearchWord = new ArrayList();
    	for(int i=0; i<list.size(); i++) {
    		List listRow = new ArrayList(5);
    		for(int r=0; r<5; r++) {
	    		String[] keyData = new String[2];
    			if(r>0) {
    				i++;
    			}
	    		if(i<list.size()) {
		    		Object[] keyInfo = (Object[])list.get(i);
		    		keyData[0] = String.valueOf(keyInfo[0]);
		    		keyData[1] = (String)((List)keyInfo[1]).get(0);
    			}
	    		listRow.add(keyData);
	    		if("ヤ".equals(keyData[1]) || "ユ".equals(keyData[1])
	    				|| "ワ".equals(keyData[1]) || "ヲ".equals(keyData[1])) {
	    			r++;
	    			listRow.add(new String[2]);
	    		}
    		}
    		listSearchWord.add(listRow);
    	}
    	return listSearchWord;
    }
}
