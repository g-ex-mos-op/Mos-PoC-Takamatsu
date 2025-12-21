package jp.co.isid.mos.bird.config.campaignmasterregist.logic;

import java.util.List;

/**
 * キャンペーンメニュー情報の取得 Interface
 * @author xnkusama
 *
 */
public interface GetMenuListLogic {
    /**
     * キャンペーンメニュー情報取得
     * @param campId
     * @param kakoFlg
     * @return
     */
    public List execute(String campId, boolean kakoFlg);
}
