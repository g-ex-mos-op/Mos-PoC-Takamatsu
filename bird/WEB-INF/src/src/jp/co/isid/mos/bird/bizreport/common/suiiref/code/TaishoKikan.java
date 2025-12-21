/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.suiiref.code;

import java.util.List;

import jp.co.isid.mos.bird.common.code.AbstractCodeUtil;
import jp.co.isid.mos.bird.common.code.UserType;

/**
 * 対象期間定数クラス
 * 
 * 作成日:2013/04/22
 * @author xkinu
 *
 */
public class TaishoKikan extends AbstractCodeUtil {
    /** コード値：任意月指定 */
    public static final String CODE_MONTH = "MONTH";
    /** コード値：年度 */
    public static final String CODE_NENDO   = "NENDO";
 
    /** 対象条件配列：本部用 */
    public static final String[][] CODE_TABLE_HONBU = new String [][]{
    	{CODE_MONTH, "任意月指定"},
    	{CODE_NENDO, "年度"}};

    /** 対象条件配列：オーナー用 */
    public static final String[][] CODE_TABLE_ONER = new String [][]{
    	{CODE_MONTH, "任意月指定"}};

    /** 対象条件配列：店舗ユーザ用 */
    public static final String[][] CODE_TABLE_MISE = new String [][]{
        {CODE_MONTH, "任意月指定"}};

    /**
     * 外部からインスタンス化できない
     */
    private TaishoKikan() {
    	super();
    }
 
    /**
     * 対象期間リストを取得する<br>
     * @param 	userType	ユーザタイプ
     * @return	List 		対象期間リスト
     */
    public static List getUIListPullDownList(String userType) {
        //本部ユーザの時
    	if (UserType.isHonbu(userType)) {
    		return getUIListPullDownList(CODE_TABLE_HONBU); 
    	} 
        //オーナーの時
        else if(UserType.isOner(userType)) {
    		return getUIListPullDownList(CODE_TABLE_ONER);  
    	}
        //店舗ユーザの時
        else if(UserType.isTenpo(userType)) {
            return getUIListPullDownList(CODE_TABLE_MISE);  
        }
    	return null;   
    }

    /**
     * コードの名称を取得する<br>
     * @param   code        コード
     * @return  String      コード名称
     */
    public static String getName(String code) {
        return getName(code, CODE_TABLE_HONBU);
    }

}
