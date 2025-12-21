package jp.co.isid.mos.bird.entry.hanyoapplication.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.entity.MstMise;

/**
 * 管理会社情報取得
 * @author kusama
 */
public interface MstMiseDao {

    public static final Class BEAN = MstMise.class;
    public static final String getCountMise_ARGS = "companyCd, miseCd";
    public static final String getMise_ARGS = "companyCd, onerCd, sysDt";
    public static final String selectMiseList_ARGS = "companyCd, onerCd";

    /**
     * 対象店舗の件数取得
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List getCountMise(String companyCd, String miseCd);
    
    /**
     * オーナー保有店舗一覧の取得（スタッフマスタに登録されている店舗の一覧）
     * @param String companyCd 会社コード
     * @param String onerCd オーナーコード
     * @return List
     */
    public List getMise(String companyCd, String onerCd, String sysDt);
    
    /**
     * オーナー保有店舗一覧の取得（店マスタに登録されている店舗の一覧）
     * @param companyCd
     * @param onerCd
     * @return
     */
    public List selectMiseList(String companyCd, String onerCd);
}