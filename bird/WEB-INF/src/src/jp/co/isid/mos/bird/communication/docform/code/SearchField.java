package jp.co.isid.mos.bird.communication.docform.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;
import jp.co.isid.mos.bird.communication.docform.common.DocFormCont;

/**
 * 対象コード定数クラス
 * 
 * 作成日:2008/12/11
 * @author xkinu
 *
 */
public class SearchField extends CodeUtil {

    /** 対象：文書・フォーム */
    public static final String FIELD_DOCFORM = "1";
    /** 対象：文書 */
    public static final String FIELD_DOC = DocFormCont.INFO_SHU_BUNSHO;
    /** 対象：フォーム */
    public static final String FIELD_FORM = DocFormCont.INFO_SHU_FORM;
 
    /** 対象リスト */
    public static final String[][] LIST = new String [][]{
    	{FIELD_DOCFORM, "文書・フォーム"}
        ,	{FIELD_DOC, "文書"}
        ,	{FIELD_FORM, "フォーム"}
    };

    /**
     * 外部からインスタンス化できない
     */
    private SearchField() {
    	super();
    }

    /**
     * コードの名称を取得する<br>
     * @param	code		コード
     * @return	String 		コード名称
     */
    public static String getName(String code) {
   		return CodeUtil.getName(code, LIST); 
    }
    /**
     * リストを取得する<br>
     * @return	List　リスト
     */
    public static List getPullDownList() {
    	return getPullDownList(LIST);
    }
    /**
     * リストを取得する<br>
	 * 渡されたコード分のリストを作成する。
	 * @param 	arrCode		コード配列
	 * @return	List		リスト(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
     */
    public static List getPullDownList(String[] arrCode) {
    	return getPullDownList(LIST, arrCode);
    }
    /**
     * カテゴリ用リストを取得する<br>
     * @return	List　リスト
     */
    public static List getPullDownListCate() {
		return getPullDownList(new String[] {FIELD_DOC, FIELD_FORM});
    }
}
