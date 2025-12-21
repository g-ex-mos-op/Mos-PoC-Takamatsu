/**
 * 
 */
package jp.co.isid.mos.bird.common.code;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * 総合検索対象画面
 * 
 * 作成日:2008/12/17
 * @author xkinu
 * 
 * 更新日:2013/09/03 周春建　201308-004_e-mosslesツールバーにお知らせ（タイトル検索）を追加
 *
 */
public class SearchRequestViewId extends CodeUtil {
	/** 対象画面：お知らせ（タイトル検索）*/
	public static final String TAITORU= "BCM002V011";
    /** 対象画面：通達 */
//    public static final String TUTATSU= "BCM002V01";
	public static final String TUTATSU= "BCM002V012";
    /** C対象画面：文書・フォーム */
    public static final String DOCFORM= "BCM004V01";
    /** 対象画面：画面メニュー */
    public static final String SCREEN= "BCO013V01";
    /** 対象画面：店・オーナー */
    public static final String MISEONER= "BCO014V01";
    
    /** 対象条件配列 */
    public static final String[][] LIST = new String [][]{
    	{TAITORU, InfoShu.TUTATU_NAME+"(タイトル検索)"},
    	{TUTATSU, InfoShu.TUTATU_NAME+"(全文検索)"},
        {DOCFORM, InfoShu.BUNSHO_NAME + "・" + InfoShu.FORM_NAME},
        {SCREEN, "画面メニュー"}, 
    	};
    /** 対象条件配列：本部用 */
    public static final String[][] LIST_HONBU = new String [][]{
    	{MISEONER, "店・オーナー"}};

    /**
     * コードの名称を取得する<br>
     * @param	code		コード
     * @return	String 		コード名称
     */
    public static String getName(String code) {
   		return CodeUtil.getName(code, LIST); 
    }
	/**
	 * プルダウンリストを取得する 
	 * 
	 * @param userTypeCd
	 * @return
	 */
    public static List getPullDownList(String userTypeCd) {
		List pullDownList = new ArrayList();
		for(int i = 0; i < LIST.length; i++) {
			SelectItem item = new SelectItem(LIST[i][0], LIST[i][1]);
			pullDownList.add(item);
		}
		if(UserType.HONBU.equals(userTypeCd)) {
			for(int i = 0; i < LIST_HONBU.length; i++) {
				SelectItem item = new SelectItem(LIST_HONBU[i][0], LIST_HONBU[i][1]);
				pullDownList.add(item);
			}
		}
		return pullDownList;
	}
}
