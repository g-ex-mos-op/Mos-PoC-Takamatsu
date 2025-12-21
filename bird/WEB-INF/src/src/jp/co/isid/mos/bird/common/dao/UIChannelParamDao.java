package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIChannelParam;

/**
 * ユーザー作成可否フラグ、オーナーコードの取得
 *
 * 作成日:2018/04/27
 * @author caiweimei
 *
 */
public interface UIChannelParamDao {

    public static final Class BEAN = UIChannelParam.class;
    public static final String select_ARGS = "userId";

    /**
     * ユーザー作成可否フラグ、オーナーコードの取得
     * @param userId     ユーザID
     * @return List      検索結果
     */
    public List select(String userId);
}
