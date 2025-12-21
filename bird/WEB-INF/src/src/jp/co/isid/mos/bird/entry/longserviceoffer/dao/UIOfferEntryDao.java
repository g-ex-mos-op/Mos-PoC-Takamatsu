/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferEntry;

/**
 * 永年勤続申請者情報（UIOfferEntryDao）
 * @author narita
 */
public interface UIOfferEntryDao {

    public static final Class BEAN = UIOfferEntry.class;
    
    /**
     * 永年勤続申請者情報リスト取得時のパラメータ
     */
    public static final String getOfferEntry_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";
    
    /**
     * 永年勤続申請者情報リスト取得時のパラメータ
     */
    public static final String getOfferEntrySeqNo_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd, seqNo";
    
    /**
     * 永年勤続申請者情報の新規登録パラメータ
     */
    public static final String insertOfferEntry_ARGS = "uIOfferEntry";
    
    /**
     * 永年勤続申請者情報の更新パラメータ
     */
    public static final String updateOfferEntry_ARGS = "uIOfferEntry";
    
    /**
     * 永年勤続申請者情報の削除パラメータ
     */
    public static final String deleteOfferEntry_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd, seqNo";
    
    /**
     * 永年勤続申請者情報の最大ソートＮｏパラメータ
     */
    public static final String getMaxSeqNo_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";
    
    /**
     * 永年勤続申請者情報のカウント取得時のパラメータ
     */
    public static final String getOfferEntryCount_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";
    
    /**
     * 永年勤続申請者情報の取得
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー年
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @return UIOfferEntry 永年勤続申請者情報
     */
    public List getOfferEntry(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);
    
    /**
     * 永年勤続申請者情報のソート番号の登録チェック
     * @param uiOfferEntry
     * @return int カウント
     */
    public int getOfferEntrySeqNo(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd, String seqNo);
    
    /**
     * 永年勤続申請者情報の新規登録
     * @param uiOfferEntry
     */
    public int insertOfferEntry(UIOfferEntry uIOfferEntry);

    /**
     * 永年勤続申請者情報の更新
     * @param uiOfferEntry
     */
    public int updateOfferEntry(UIOfferEntry uIOfferEntry);

    /**
     * 永年勤続申請者情報の一括削除（指定のソート番号以上を一括削除）
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー年
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @param seqNo ソート番号
     * @return int 結果値
     */
    public int deleteOfferEntry(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd, String seqNo);
    
    /**
     * 永年勤続申請者情報の最大ソートＮｏの取得
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー年
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @return String 結果値
     */
    public String getMaxSeqNo(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);
    
    /**
     * 永年勤続申請者情報のカウント取得
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー年
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @return int 結果値
     */
    public int getOfferEntryCount(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);
}
