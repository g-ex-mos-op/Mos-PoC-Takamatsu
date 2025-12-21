package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dao.UIChannelParamDao;
import jp.co.isid.mos.bird.common.logic.GetChannelParamLogic;


/**
 * ユーザー作成可否フラグ、オーナーコードの取得
 *
 * 作成日:2018/04/27
 * @author caiweimei
 *
 */
public class GetChannelParamLogicImpl implements GetChannelParamLogic {

	/* UIChannelParamDao */
    private UIChannelParamDao dao;

    /**
     * 外部リンク取得Daoを取得します。
     * @return UIChannelParamDao
     */
    public UIChannelParamDao getChannelParamDao() {
        return dao;
    }
    /**
     * 外部リンク取得Daoを設定します。
     * @param UIChannelParamDao
     */
    public void setChannelParamDao(UIChannelParamDao dao) {
        this.dao = dao;
    }
    /**
     * 外部リンク取得処理
     */
    public List execute(String userId) {
        return getChannelParamDao().select(userId);
    }
}
