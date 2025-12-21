package jp.co.isid.mos.bird.common.logic;

import java.util.List;

/**
 * モスチャンネルパラメータの取得ロジックインターフェース
 *
 * 作成日:2017/04/27
 * @author caiweimei
 *
 */
public interface GetChannelParamLogic {

    /**
     * ユーザー作成可否フラグ、オーナーコードの取得
     *
     * @param userId ユーザーID
     * @return ユーザー作成可否フラグ、オーナーコード
     */
    public List execute(String userId);
}
