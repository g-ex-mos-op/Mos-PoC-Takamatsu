package jp.co.isid.mos.bird.bizreport.common.suiiref.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.AbstractCodeUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * 推移表共通CODE【対象条件コード定数クラス】
 * コードデータのリストの生成、コードデータからの名称取得できる機能を保持しているクラスです。
 * 
 * 作成日:2013/04/15
 * @author xkinu
 *
 */
public class TaishoJoken extends AbstractCodeUtil{
    
    /** コード値：全社(全店) */
    public static final String CODE_ALL      = jp.co.isid.mos.bird.common.code.TaishoJoken.CODE_ALL;
    /** コード値：セグメント */
    public static final String CODE_SEGMENT  = jp.co.isid.mos.bird.common.code.TaishoJoken.CODE_SEGMENT;
    /** コード値：事業本部 */
    public static final String CODE_JIGYOU   = jp.co.isid.mos.bird.common.code.TaishoJoken.CODE_JIGYOU;
    /** コード値：営業エリア */
    public static final String CODE_SLAREA   = jp.co.isid.mos.bird.common.code.TaishoJoken.CODE_SLAREA;
    /** コード値：支部 */
    public static final String CODE_SIBU     = jp.co.isid.mos.bird.common.code.TaishoJoken.CODE_SIBU;
    /** コード値：店舗(個店) */
    public static final String CODE_MISE     = jp.co.isid.mos.bird.common.code.TaishoJoken.CODE_MISE;
    /** コード値：オーナー */
    public static final String CODE_ONER     = jp.co.isid.mos.bird.common.code.TaishoJoken.CODE_ONER;

    /** 対象条件配列：本部用：(モス・SVなし) */
    public static final String[][] CODE_TABLE_HONBU = new String [][]{
    	{CODE_ALL, "全社"},
        {CODE_SEGMENT, "セグメント"},
        {CODE_JIGYOU, "事業本部"},
    	{CODE_SLAREA, "営業エリア"},
        {CODE_SIBU, "支部"},
        {CODE_MISE, "個店"}};

    /** 対象条件配列：本部用：(モス・SV) */
    public static final String[][] CODE_TABLE_HONBU_SV = new String [][]{
        {CODE_SLAREA, "営業エリア"},
        {CODE_SIBU, "支部"},
        {CODE_MISE, "個店"}};

    /** 対象条件配列：本部用：(モス以外・SVなし) */
    public static final String[][] CODE_TABLE_HONBU_NOTMOS = new String [][]{
        {CODE_ALL, "全社"},
        {CODE_SIBU, "支部"},
        {CODE_MISE, "個店"}};

    /** 対象条件配列：本部用：(モス以外・SV) */
    public static final String[][] CODE_TABLE_HONBU_NOTMOS_SV = new String [][]{
        {CODE_SIBU, "支部"},
        {CODE_MISE, "個店"}};

    /** 対象条件配列：オーナー用 */
    public static final String[][] CODE_TABLE_ONER = new String [][]{
        {CODE_ALL, "全店"},
        {CODE_MISE, "店舗"}};

    /** 対象条件配列：店舗用 */
    public static final String[][] CODE_TABLE_MISE = new String [][]{
        {CODE_MISE, "店舗"}};
 
    /**
     * 外部からインスタンス化できない
     */
    private TaishoJoken() {
    	super();
    }  

    /**
     * 対象条件リストを取得する<br>
     * @param  String   ユーザタイプ
     * @param  String   会社コード
     * @param  limitKbn 制限区分(本部ユーザー時のみ使用)
     * @return List　   対象条件リスト
     */
    public static List getUIListsPullDownList(String userTypeCd, String companyCd, boolean limitKbn) {

        //入力チェック
        if(userTypeCd == null || userTypeCd.length() == 0) {
            return null;
        }
        if(companyCd == null || companyCd.length() == 0) {
            return null;
        }
        
        //本部ユーザの時
        if (UserType.isHonbu(userTypeCd)) {
        	//会社＝モスフードサービスの場合
            if (CommonUtil.COMPANY_CD_MOS.equals(companyCd)) {
            	if(limitKbn) {
            		//支部制限時
            		return getUIListPullDownList(CODE_TABLE_HONBU_SV);
            	}
            	//支部制限なし
                return getUIListPullDownList(CODE_TABLE_HONBU); 
            } 
        	//会社＝モスフードサービス以外の場合
            else{
            	if(limitKbn) {
            		//支部制限時
                    return getUIListPullDownList(CODE_TABLE_HONBU_NOTMOS_SV); 
                }
            	//支部制限なし
                return getUIListPullDownList(CODE_TABLE_HONBU_NOTMOS); 
            }
        }
        //オーナーの時
        else if(UserType.isOner(userTypeCd)) {
            return getUIListPullDownList(CODE_TABLE_ONER);  
        } 
        
        //店舗ユーザの時
        else if(UserType.isTenpo(userTypeCd)) {
            return getUIListPullDownList(CODE_TABLE_MISE);  
        }
        return null;   
    }

    /**
     * コードの名称を取得する<br>
     * @param  String  ユーザタイプコード
     * @param	String	コード
     * @return	String 	コード名称
     */
    public static String getName(String userTypeCd, String code) {
        
        //入力チェック
        if(userTypeCd == null || userTypeCd.length() == 0) {
            return null;
        }
        if(code == null || code.length() == 0) {
            return null;
        }

        
        //本部ユーザの時
        if (UserType.isHonbu(userTypeCd)) {
            return getName(code, CODE_TABLE_HONBU); 
        } 
        //オーナーの時
        else if(UserType.isOner(userTypeCd)) {
            return getName(code, CODE_TABLE_ONER);  
        } 
        //店舗ユーザの時
        else if(UserType.isTenpo(userTypeCd)) {
            return getName(code, CODE_TABLE_MISE);  
        }
    	return null;
    }
}
