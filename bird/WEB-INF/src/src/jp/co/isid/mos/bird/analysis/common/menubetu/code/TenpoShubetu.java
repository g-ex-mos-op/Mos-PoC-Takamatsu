package jp.co.isid.mos.bird.analysis.common.menubetu.code;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.UILists;

/**
 * 店舗種別コード定数クラス
 * 
 * @author xkinu
 */
public class TenpoShubetu extends CodeUtil {

    /**
     * 店舗種別：全店
     */
    public static final String CODE_ALL = "ALL";
    /**
     * 店舗種別：前年対象店
     */
    public static final String CODE_ZENNEN = "1";
    /**
     * 店舗種別：予算対象店
     */
    public static final String CODE_YOSAN = "2";
    /**
     * 店舗種別：新店
     */
    public static final String CODE_SIN = "3";

    /** 対象店舗配列 */
    public static final String[][] CODE_TABLE = new String [][]{
    	{CODE_ALL, "全店"},
    	{CODE_ZENNEN, "前年対象店"},
    	{CODE_YOSAN, "予算対象店"},
    	{CODE_SIN, "新店"}};

    /** 対象店舗配列：本部ユーザ用 */
    public static final String[][] CODE_TABLE_HONBU = new String [][]{
        {CODE_ALL, "全店"},
        {CODE_ZENNEN, "前年対象店"}};

    /** 対象店舗配列：オーナー用 */
    public static final String[][] CODE_TABLE_ONER = new String [][]{
        {CODE_ALL, "全店"}};

    /** 対象店舗配列：店舗ユーザ用 */
    public static final String[][] CODE_TABLE_TENPO = new String [][]{
        {CODE_ALL, "全店"}};
 
    /**
     * 外部からインスタンス化できない
     */
    private TenpoShubetu() {
    	super();
    }  
    /**
     * ユーザタイプコード別コードテーブル取得処理
     * @param userTypeCd
     * @return
     */
    private static String[][] getCodeTable(String userTypeCd) {
        if(UserType.HONBU.equals(userTypeCd)){
            return CODE_TABLE_HONBU;
        }
        // ユーザータイプがオーナーの場合
        else if (UserType.ONER.equals(userTypeCd)) {
            return CODE_TABLE_ONER;
        }
        // ユーザータイプが店舗の場合
        else if (UserType.TENPO.equals(userTypeCd)){
            return CODE_TABLE_TENPO; 
        }
        return null;
    }
    /**
     * コードの名称を取得する<br>
     * @param	code	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
    	return getName(code, CODE_TABLE);    
    }

    /**
     * 店舗種別リストを取得する<br>
     * @return	List　店舗種別リスト
     */
    public static List getPullDownList() {
    	return getPullDownList(CODE_TABLE);
    }

    /**
     * ユーザタイプに応じた店舗種別リストを取得する<br>
     * @param   userTypeCd　ユーザタイプコード
     * @return  List　  店舗種別リスト
     */
    public static List getPullDownList(String userTypeCd) {
        return getPullDownList(getCodeTable(userTypeCd));
    }

    /**
     * 店舗種別リストを取得する<br>
	 * 渡されたコード分のリストを作成する。
	 * @param 	arrCode		コード配列
	 * @return	List		店舗種別リスト(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
     */
    public static List getPullDownList(String[] arrCode) {
    	return CodeUtil.getPullDownList(CODE_TABLE, arrCode);
    }
	/**
	 * プルダウンリストを取得する 
	 * @param 	codeTable	ユーザータイプコード
	 * @return	List		プルダウンリスト
	 */
    public static List getUIListPullDownList(String userTypeCd) {
		String[][] codeTable = getCodeTable(userTypeCd);
		List pullDownList = new ArrayList();
		for(int i = 0; i < codeTable.length; i++) {
			UILists item = new UILists();
			item.setKey(codeTable[i][0]);
			item.setKeyName(codeTable[i][1]);
			pullDownList.add(item);
		}
		return pullDownList;
	}
	/**
	 * プルダウンリストを取得する
	 * 渡されたコード分のリストを作成する
	 * @param 	userTypeCd	ユーザータイプコード
	 * @param 	arrCode		コード配列
	 * @return	List		UIListオブジェクト保持プルダウンリスト(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
	 */
    public static List getUIListPullDownList(String userTypeCd, String[] arrCode) {
		String[][] codeTable = getCodeTable(userTypeCd);
		List pullDownList = new ArrayList();

		if(arrCode == null) {
			return pullDownList;
		}

		for(int i= 0; i < arrCode.length; i++) {
			String code = arrCode[i];

			for(int j = 0; j < codeTable.length; j++) {
				if (codeTable[j][0].equals(code)) {
					UILists item = new UILists();
					item.setKey(codeTable[j][0]);
					item.setKeyName(codeTable[j][1]);
					pullDownList.add(item);
					break;
				}
			}
		}

		return pullDownList;
	}
}
