package jp.co.isid.mos.bird.common.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 外部リンク情報取得ロジックインターフェース
 *
 * 作成日:2009/01/15
 * @author xkinu
 *
 */
public interface GetOutLinkLogic {

    /**
     * 外部リンク情報取得処理
     *
     * @param userInfo
     * @param menuDispKbn
     * @param outLinkId
     * @return
     */
    public List execute(BirdUserInfo userInfo, String[] menuDispKbn, String outLinkId,String dougaCd);
}
