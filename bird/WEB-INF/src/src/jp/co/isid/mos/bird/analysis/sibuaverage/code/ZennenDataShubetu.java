/**
 * 
 */
package jp.co.isid.mos.bird.analysis.sibuaverage.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.AbstractCodeUtil;
import jp.co.isid.mos.bird.common.code.UserType;

/**
 * 前年データ種別定数クラス
 * 
 * 支部平均比較画面は、営業日報・売上推移表とは違い
 * 本部時とオーナー時の前年同曜は”前年同病日”と表示されている。
 * 
 * 作成日:2012/11/14
 * @author xkinu
 *
 */
public class ZennenDataShubetu extends AbstractCodeUtil {

    /** 前年データ種別：前年同月 */
    public static final String CODE_DOGETU = "DOGETU";

    /** 前年データ種別：前年同曜 */
    public static final String CODE_DOYO = "DOYO";

    /** 前年データ種別：前年同日 */
    public static final String CODE_DOJITU = "DOJITU";
    
    private static final String LABEL_DOGETU = "前年同月";
    private static final String LABEL_DOYO = "前年同曜日";
    private static final String LABEL_DOJITU = "前年同日";
  
    /** 前年データ種別:本部用 */
    public static final String[][] CODE_TABLE_HONBU = new String [][]{
    	{CODE_DOGETU, LABEL_DOGETU},
    	{CODE_DOYO, LABEL_DOYO},
    	{CODE_DOJITU, LABEL_DOJITU}};
 
    /** 前年データ種別:オーナー用 */
    public static final String[][] CODE_TABLE_ONR = new String [][]{
    	{CODE_DOGETU, LABEL_DOGETU},
    	{CODE_DOYO, LABEL_DOYO},
    	{CODE_DOJITU, LABEL_DOJITU}};
 
    /**
     * 外部からインスタンス化できない
     */
    private ZennenDataShubetu() {
    	super();
    }

    /**
     * コードの名称を取得する
     * @param   code        コード
     * @param   userType    ユーザタイプ
     * @return  String      コード名称
     */
    public static String getName(String code, String userType) {
        // ユーザタイプがオーナーの場合
        if (UserType.isOner(userType)) {
            return getName(code, CODE_TABLE_ONR);           
        }
        return getName(code, CODE_TABLE_HONBU);  
    }

    /**
     * 前年データ種別リストを取得する
     * @param   userType        ユーザタイプ
     * @param 	tenpoShubetu	店舗種別
     * @return	List 			前年データ種別リスト
     */
    public static List getPullDownList(String userType) {
    	// ユーザータイプが本部の場合
    	if (UserType.isHonbu(userType)) {
    		return getPullDownList(CODE_TABLE_HONBU); 
    		
    	}
    	// ユーザタイプがオーナーの場合
    	else if (UserType.isOner(userType)) {
    		return getPullDownList(CODE_TABLE_ONR);    		
    	}

    	return null;    	
    }
}
