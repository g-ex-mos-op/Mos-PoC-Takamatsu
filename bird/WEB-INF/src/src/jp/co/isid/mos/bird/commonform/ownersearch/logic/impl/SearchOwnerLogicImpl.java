/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.ownersearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.ownersearch.dao.UIGroupTogoOwnerDao;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchConditionDto;
import jp.co.isid.mos.bird.commonform.ownersearch.logic.SearchOwnerLogic;

/**
 * オーナ検索処理ロジック
 * @author itamoto
 */
public class SearchOwnerLogicImpl implements SearchOwnerLogic {

    /* UIGroupTogoOnerDao */
    private UIGroupTogoOwnerDao uiGroupTogoOwnerDao;

    /**
     * オーナ検索Daoを取得します。
     * @return uiGroupTogoOnerDao
     */
    public UIGroupTogoOwnerDao getUiGroupTogoOwnerDao() {
        return uiGroupTogoOwnerDao;
    }

    /**
     * オーナ検索Daoを設定します。
     * @param uiGroupTogoOnerDao
     */
    public void setUiGroupTogoOwnerDao(UIGroupTogoOwnerDao uiGroupTogoOwnerDao) {
        this.uiGroupTogoOwnerDao = uiGroupTogoOwnerDao;
    }

    /**
     * オーナ検索処理
     * @param onerSearchConditionDto
     * @param indexSearchList
	 */
	public List execute(OwnerSearchConditionDto ownerSearchConditionDto, List indexSearchList) {
// add start xkhata 2006/05/16 条件追加対応
        String sortType = null;
        String inEnd = null;

        if ( !ownerSearchConditionDto.getSortSeq().equals( "ONER_CD" ) ) {
            sortType = new String();
        }
        
        if ( !ownerSearchConditionDto.getInEnd().equals( "inEnd" ) ) {
            inEnd = new String();
        }
// end
        
        String[] miseNameKna = (String[]) indexSearchList.toArray(new String[4]);
        return getUiGroupTogoOwnerDao().select(
                ownerSearchConditionDto.getKaisyaCd(),
                ownerSearchConditionDto.getSibuCd(),
                (ownerSearchConditionDto.getOnerNameKj() == null) ? null : "%" + ownerSearchConditionDto.getOnerNameKj() + "%",
                ownerSearchConditionDto.getOnerCd(),
                (ownerSearchConditionDto.getMiseNameKj() == null) ? null : "%" + ownerSearchConditionDto.getMiseNameKj() + "%",
                ownerSearchConditionDto.getMiseCd(),
                (ownerSearchConditionDto.getSvNameKj() == null) ? null : "%" + ownerSearchConditionDto.getSvNameKj() + "%",
                ownerSearchConditionDto.getSvCd(),
                (miseNameKna[0] == null) ? null : miseNameKna[0] + "%",
                (miseNameKna[1] == null) ? null : miseNameKna[1] + "%",
                (miseNameKna[2] == null) ? null : miseNameKna[2] + "%",
                (miseNameKna[3] == null) ? null : miseNameKna[3] + "%",
                sortType,
                inEnd);
	}
}
