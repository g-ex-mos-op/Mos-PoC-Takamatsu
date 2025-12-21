package jp.co.isid.mos.bird.commonform.miseonersearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.miseonersearch.entity.UISearchInfo;

/**
 * メニュー検索画面
 * 検索情報取得Daoインターフェース
 * 
 * @author xkinu
 *
 */
public interface UISearchInfoDao {
    /**
     * 検索情報格納エンティティクラス
     */
    public static final Class BEAN = UISearchInfo.class;
    
    /**
     * 検索情報取得時のパラメータ
     */
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, sysDate, searchWord";
    /**
     * 検索情報取得
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param sysDate
     * @param searchWord
     * @return
     */
    public List select(String userId, String userTypeCd, boolean limitFlg, String sysDate, String searchWord);

}
