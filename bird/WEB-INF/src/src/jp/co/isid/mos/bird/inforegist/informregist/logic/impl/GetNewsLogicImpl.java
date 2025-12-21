/*
 * 作成日: 2006/2/13
 */
package jp.co.isid.mos.bird.inforegist.informregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.inforegist.informregist.dao.UINewsDao;
import jp.co.isid.mos.bird.inforegist.informregist.logic.GetNewsLogic;

/**
 * 既存のお知らせ一覧取得処理ロジック
 * @author itamoto
 */
public class GetNewsLogicImpl implements GetNewsLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BIF004L01";

    /* UINewsDao */
    private UINewsDao uiNewsDao;

	/**
     * 登録内容のチェック取得Daoを取得します。
	 * @return uiNewsDao を戻します。
	 */
	public UINewsDao getUiNewsDao() {
		return uiNewsDao;
	}
	/**
     * 登録内容のチェック取得Daoを設定します。
	 * @param uiNewsDao uiNewsDao を設定。
	 */
	public void setUiNewsDao(UINewsDao uiNewsDao) {
		this.uiNewsDao = uiNewsDao;
	}

    /**
     * 既存のお知らせ一覧取得処理
     * @param informRegistDto
     */
    public List execute(String regDate, String userId) {
    	return uiNewsDao.getNews(regDate + "%", userId);
    }
}
