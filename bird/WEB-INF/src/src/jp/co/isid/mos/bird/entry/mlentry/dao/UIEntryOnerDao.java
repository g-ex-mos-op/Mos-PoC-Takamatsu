package jp.co.isid.mos.bird.entry.mlentry.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryOner;

/**
 * エントリーオーナー宛先情報を取得する
 * @author Aspac
 */
public interface UIEntryOnerDao {

    public static final Class BEAN = UIEntryOner.class;
    public static final String getEntryOner_ARGS  = "entryCd, entryYear, entryKai, companyCd, onerCd";
    public static final String insertEntryOner_ARGS = "uiEntryOwner";
    public static final String updateEntryOner_ARGS = "uiEntryOwner";
    public static final String deleteEntryOner_ARGS = "uiEntryOwner";
    public static final String updateEntryOner_PERSISTENT_PROPS 
            = "entryCd, entryYear, entryKai, companyCd,"
            + "onerCd, atesakiKbn, name, tel, zip, address1, address2, address3,"
            + "soufuName, jobType, lastUser, lastPgm";

    /**
     * エントリーオーナー宛先情報の新規登録
     * @param uiEntryOwner
     */
    public int insertEntryOner(UIEntryOner uiEntryOner);

    /**
     * エントリーオーナー宛先情報の更新
     * @param uiEntryOwner
     */
    public int updateEntryOner(UIEntryOner uiEntryOner);
    
    /**
     * エントリーオーナー宛先情報の削除
     * @param uiEntryOwner
     */
    public int deleteEntryOner(UIEntryOner uiEntryOner);
    
    /**
     * エントリーマスタ管理の取得(getEntryOner)
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @return List 検索結果
     */
    public List getEntryOner(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);

}