/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.hanyoviewlist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoviewlist.dao.UIHanyoListDao;
import jp.co.isid.mos.bird.entry.hanyoviewlist.dto.HanyoViewListDto;
import jp.co.isid.mos.bird.entry.hanyoviewlist.logic.GetHanyoListLogic;

/**
 * 研修(全て/出張/更新)一覧取得ロジック
 * @author Nakajima
 */
public class GetHanyoListLogicImpl implements GetHanyoListLogic {

    /* ロジックID */
    private static final String LOGIC_ID = "BEN006L01";

    /**
     * 研修(全て/出張/更新)一覧情報（UIHanyoListDao）
     */
    private UIHanyoListDao uIHanyoListDao;
    
	/**
	 * 研修(全て/出張/更新)一覧Daoの設定
	 * @return codCompanyJohoDao を戻します。
	 */
	public UIHanyoListDao getUIHanyoListDao() {
		return uIHanyoListDao;
	}
	/**
	 * 研修(全て/出張/更新)一覧Daoの設定
	 * @param UIHanyoListDao uIHanyoListDao を設定。
	 */
	public void setUIHanyoListDao(UIHanyoListDao uIHanyoListDao) {
		this.uIHanyoListDao = uIHanyoListDao;
	}

	/**
	 * 研修(出張/更新)一覧の検索
	 * @return 検索結果
	 */
	public List execute(HanyoViewListDto hanyoViewListCommonDto) {
        
        String sysdate     = hanyoViewListCommonDto.getSysDate();
        String sysNextDate = hanyoViewListCommonDto.getSysNextDate();
        String entryCd     = hanyoViewListCommonDto.getEntryCd();
        
        List hanyoList;
        
        // 研修(全て/出張/更新)一覧取得
        hanyoList = uIHanyoListDao.getHanyoListInfo(sysdate, sysNextDate, entryCd);
        
		return hanyoList;
	}
}
