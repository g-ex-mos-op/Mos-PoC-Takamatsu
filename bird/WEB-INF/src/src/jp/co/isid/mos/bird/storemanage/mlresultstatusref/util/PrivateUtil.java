/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.util;

/**
 * 
 * 作成日:2009/09/02
 * @author xkinu
 *
 */
public class PrivateUtil {
	/** 画面ID:BSM007 */
	public static final String SCREEN_ID = "BSM007";

    public static final String condition_VIEW_ID		= "BSM007V01";
    public static final String result_VIEW_ID			= "BSM007V02";
    public static final String onerSearch_VIEW_ID		= "BCO006V01";
    public static final String miseSearch_VIEW_ID		= "BCO008V01";
    
	/** 対象条件：全て*/
	public static final int TAISHO_JOKEN_ALL = 3;
	/** 対象条件：支部(取込)*/
	public static final int TAISHO_JOKEN_AREADAI = 0;
	/** 対象条件：オーナー*/
	public static final int TAISHO_JOKEN_OENR = 1;
	/** 対象条件：店舗*/
	public static final int TAISHO_JOKEN_MISE = 2;
	
	/** ダウンロードタイプ：一括ダウンロード*/
	public static final int DL_TYPE_IKKATU = 2;
	/** ダウンロードタイプ：年度ウンロード*/
	public static final int DL_TYPE_NENDO = 1;
	/** ダウンロードタイプ：年度回ダウンロード*/
	public static final int DL_TYPE_NENDOKAI = 0;
	/** 条件部：表示 */
	public static final int JOKEN_VIEW_ON = 1;
	/** 条件部：非表示 */
	public static final int JOKEN_VIEW_OFF = 0;
	
}
