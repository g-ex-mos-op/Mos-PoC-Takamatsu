package jp.co.isid.mos.bird.analysis.sibuaverage.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.sibuaverage.entity.UIOnerInfo;

/**
 * オーナー情報取得DAOクラス
 * 
 * @author xnkusama
 * 更新日:2012/11/01 検索条件変更対応 集計区分の条件を追加しました。
 */
public interface UIOnerInfoDao {

    /** オーナー情報エンティティクラス */
    public static final Class BEAN = UIOnerInfo.class;

    /** オーナー支部一覧取得のパラメータ */
    public static final String selectOnerSibuList_ARGS = "companyCd, onerCd, shukeiKbn, limitFlg, userTypeCd, userId";

    /**
     * オーナー支部一覧取得
     * 
     * @param companyCd
     * @param onerCd
     * @param shukeiKbn (2012/11/01追加)
     * @param limitFlg
     * @param userTypeCd
     * @param userId
     * @return
     */
    public List selectOnerSibuList(
                    String companyCd,
                    String onerCd,
                    String shukeiKbn,
                    boolean limitFlg,
                    String userTypeCd,
                    String userId);

}