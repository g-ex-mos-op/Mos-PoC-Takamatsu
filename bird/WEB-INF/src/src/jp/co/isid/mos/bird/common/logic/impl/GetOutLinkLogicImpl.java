package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dao.UIOutLinkDao;
import jp.co.isid.mos.bird.common.logic.GetOutLinkLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 外部リンク情報取得処理ロジック
 *
 * 作成日:2009/01/14
 * @author xkinu
 *
 */
public class GetOutLinkLogicImpl implements GetOutLinkLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BPO000L01";
    /* UIOutLinkDao */
    private UIOutLinkDao commonUIOutLinkDao;

    /**
     * 外部リンク取得Daoを取得します。
     * @return commonUIOutLinkDao
     */
    public UIOutLinkDao getCommonUIOutLinkDao() {
        return commonUIOutLinkDao;
    }
    /**
     * 外部リンク取得Daoを設定します。
     * @param commonUIOutLinkDao
     */
    public void setCommonUIOutLinkDao(UIOutLinkDao dao) {
        this.commonUIOutLinkDao = dao;
    }
    /**
     * 外部リンク取得処理
     */
    public List execute(BirdUserInfo userInfo, String[] menuDispKbn,  String outLinkId,String dougaCd) {
        return getCommonUIOutLinkDao().select(menuDispKbn, userInfo.getUserID(), outLinkId,dougaCd);
    }
}
