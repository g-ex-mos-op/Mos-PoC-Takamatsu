/*
 * 作成日: 2006/12/18
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.dao;

import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferMst;


/**
 * エントリーマスタ管理（UIOfferMstDao）
 * @author itamoto
 */
public interface UIOfferMstDao {

    /**
     * エントリーマスタ管理エンティティ
     */
    public static final Class BEAN = UIOfferMst.class;

    /**
     * 永年勤続責任者情報取得時のパラメータ
     */
    public static final String getOfferMst_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd, atesakiKbn";

    /**
     * 永年勤続責任者情報のカウント取得時のパラメータ
     */
    public static final String getOfferMstCount_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd, atesakiKbn";
    
    /**
     * 永年勤続責任者情報の新規登録パラメータ
     */
    public static final String insertOfferMst_ARGS = "uiOfferMst";
    
    /**
     * 永年勤続責任者情報の更新パラメータ
     */
    //public static final String updateOfferMst_ARGS = "uiOfferMst";
    public static final String updateOfferMst_PERSISTENT_PROPS = "name, tel, soufuName, jobType, lastUser, lastPgm";
           
    /**
     * 永年勤続責任者情報の削除パラメータ
     */
    public static final String deleteOfferMst_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd, atesakiKbn";
        
    /**
     * 永年勤続責任者情報を取得する
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー年
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @param atesakiKbn 宛先区分
     * @return UIOfferMst 永年勤続責任者情報
     */
    public UIOfferMst getOfferMst(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd, String atesakiKbn);
    /**
     * 永年勤続責任者情報のカウントを取得する
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー年
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @param atesakiKbn 宛先区分
     * @return int 永年勤続責任者情報カウント
     */
    public int getOfferMstCount(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd, String atesakiKbn);
    
    /**
     * エントリー管理の新規登録
     * @param uiOfferMst 永年勤続責任者情報エンティティ
     */
    public int insertOfferMst(UIOfferMst uiOfferMst);

    /**
     * エントリー管理の更新
     * @param uiOfferMst 永年勤続責任者情報エンティティ
     */
    public int updateOfferMst(UIOfferMst uiOfferMst);

    /**
     * エントリー管理の削除
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー年
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @param atesakiKbn 宛先区分
     */
    public int deleteOfferMst(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd, String atesakiKbn);
        
}
