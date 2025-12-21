package jp.co.isid.mos.bird.entry.hanyoapplication.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryOwner;

/**
 * エントリーオーナー宛先情報を取得する
 * @author kusama
 */
public interface UIEntryOwnerDao {

    public static final Class BEAN = UIEntryOwner.class;
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
    public int insertEntryOner(UIEntryOwner uiEntryOwner);

    /**
     * エントリーオーナー宛先情報の更新
     * @param uiEntryOwner
     */
    public int updateEntryOner(UIEntryOwner uiEntryOwner);
    
    /**
     * エントリーオーナー宛先情報の削除
     * @param uiEntryOwner
     */
    public int deleteEntryOner(UIEntryOwner uiEntryOwner);

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