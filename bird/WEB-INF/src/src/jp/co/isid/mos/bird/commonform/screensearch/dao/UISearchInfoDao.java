package jp.co.isid.mos.bird.commonform.screensearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.screensearch.entity.UISearchInfo;

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
    public static final String select_ARGS = "userId, searchWord";
    /**
     * 検索情報取得
     * 
     * ヘッダーのメニューに表示されていないメニューは
     * 検索結果にも取得しない。
     * 
     * @param userId
     * @param searchWord
     * @return
     */
    public List select(String userId, String searchWord);
}
