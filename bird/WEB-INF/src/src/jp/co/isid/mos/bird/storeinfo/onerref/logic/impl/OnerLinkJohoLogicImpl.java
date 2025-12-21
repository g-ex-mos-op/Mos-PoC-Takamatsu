/*
 * 作成日: 2006/3/10
 */
package jp.co.isid.mos.bird.storeinfo.onerref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.onerref.dao.CodOnerLinkJohoDao;
import jp.co.isid.mos.bird.storeinfo.onerref.dto.OwnerReferenceDto;
import jp.co.isid.mos.bird.storeinfo.onerref.logic.OnerLinkJohoLogic;

/**
 * オーナー照会リンク情報の取得ロジックアクション
 * @author itamoto
 */
public class OnerLinkJohoLogicImpl implements OnerLinkJohoLogic {

    public static final String LOGIC_ID = "BSI002L03";

    /**
     * オーナー照会リンク情報（CodOnerLinkJohoDao）
     */
    private CodOnerLinkJohoDao codOnerLinkJohoDao;
    
	/**
     * オーナー照会リンク情報（CodOnerLinkJohoDao）の設定
	 * @return codOnerLinkJohoDao を戻します。
	 */
	public CodOnerLinkJohoDao getCodOnerLinkJohoDao() {
		return codOnerLinkJohoDao;
	}
	/**
     * オーナー照会リンク情報（CodOnerLinkJohoDao）の設定
	 * @param codOnerLinkJohoDao codOnerLinkJohoDao を設定。
	 */
	public void setCodOnerLinkJohoDao(CodOnerLinkJohoDao codOnerLinkJohoDao) {
		this.codOnerLinkJohoDao = codOnerLinkJohoDao;
	}

	/**
	 * オーナー照会リンク情報の取得
	 * @return 検索結果
	 */
	public List execute(OwnerReferenceDto ownerReferenceDto) {
		return codOnerLinkJohoDao.selectOnerLink(ownerReferenceDto
				.getBirdUserInfo().getUserID());
	}
}
