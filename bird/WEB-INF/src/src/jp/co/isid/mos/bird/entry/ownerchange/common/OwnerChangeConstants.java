/*
 * 作成日:2007/01/19
 */
package jp.co.isid.mos.bird.entry.ownerchange.common;

/**
 * オーナー間異動登録　定数クラス
 * @author xkonishi
 *
 */
public class OwnerChangeConstants {
    
    /**
     * 画面ID
     */
    public static final String SCREEN_ID= "BEN023";
    
    /**
     * VIEWID
     */
    // 条件画面
    public static final String VIEW_ID_CONDITION = "BEN023V01";
    // 対象者設定画面    
    public static final String VIEW_ID_SETUP = "BEN023V02";
    // 編集画面
    public static final String VIEW_ID_EDIT = "BEN023V03";
    // 確認画面
    public static final String VIEW_ID_CONFIRM = "BEN023V04";
    // 店舗選択画面
    public static final String VIEW_ID_MISESEARCH = "BCO008V01";
    // オーナー選択画面
    public static final String VIEW_ID_OWNERSEARCH = "BCO006V01";
    // 操作エラー画面
    public static final String VIEW_ID_OPERATION_ERR = "operation.Err";
    
    /**
     * 条件区分
     * 0 : 店舗
     * 1 : オーナー
     */
    // 条件区分:店舗
    public static final String KBN_MISE = "0";
    // 条件区分:オーナー
    public static final String KBN_OWNER = "1";

    /**
     * リストの先頭
     */
    public static final int FIRST_ELEMENT = 0;
    
    /**
     * 桁数
     */
    // 店コード桁数
    public static final int MISECD_LENGTH = 5;
    // オーナーコード桁数
    public static final int OWNERCD_LENGTH = 5;    
}
