package jp.co.isid.mos.bird.entry.basicentry.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryOnerStatus;

/**
 * オーナー別エントリー状況情報を取得する
 * @author kusama
 */
public interface UIEntryOnerStatusDao {

    public static final Class BEAN = UIEntryOnerStatus.class;
    public static final String getEntryOner_ARGS  = "entryCd, entryYear, entryKai, companyCd, onerCd";
    public static final String insertEntry_ARGS = "uiEntryOnerStatus";
    public static final String updateEntry_ARGS = "uiEntryOnerStatus";
    public static final String deleteEntry_ARGS = "uiEntryOnerStatus";
    public static final String updateEntry_PERSISTENT_PROPS 
                                        = "entryCd, entryYear, entryKai, companyCd, onerCd"
                                        + "entryFlg, lastUser, lastPgm";

    /**
     * オーナー別エントリー状況の新規登録(insertEntryOner)
     * @param uiEntryOnerStatus
     */
    public int insertEntryOner(UIEntryOnerStatus uiEntryOnerStatus);

    /**
     * オーナー別エントリー状況の更新(updateEntryOner)
     * @param uiEntryOnerStatus
     */
    public int updateEntryOner(UIEntryOnerStatus uiEntryOnerStatus);

    /**
     * オーナー別エントリー状況の削除
     * @param uiEntryOnerStatus
     */
    public int deleteEntryOner(UIEntryOnerStatus uiEntryOnerStatus);

    /**
     * オーナー別エントリー状況の取得(getEntry)
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @return List 検索結果
     */
    public List getEntryOner(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);

}