package jp.co.isid.mos.bird.entry.basicentry.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.basicentry.entity.MstMise;

/**
 * 管理会社情報取得
 * @author kusama
 */
public interface MstMiseDao {

    public static final Class BEAN = MstMise.class;
    public static final String selectMiseList_ARGS = "companyCd, onerCd, sysDt";
    public static final String getMiseInfo_ARGS = "companyCd, miseCd";

    /**
     * オーナー保有店舗一覧の取得（店マスタに登録されている店舗の一覧）
     * @param companyCd
     * @param onerCd
     * @return
     */
    public List selectMiseList(String companyCd, String onerCd, String sysDt);

    /**
     * 指定店舗の店情報取得
     * @param String companyCd 会社コード
     * @param String miseCd 
     * @return List
     */
    public List getMiseInfo(String companyCd, String miseCd);
    
}