/*
 * 作成日: 2006/08/22
 */
package jp.co.isid.mos.bird.commonform.svsearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.svsearch.action.SvSearchAction;
import jp.co.isid.mos.bird.commonform.svsearch.dao.UISvDao;
import jp.co.isid.mos.bird.commonform.svsearch.dto.SvSearchConditionDto;
import jp.co.isid.mos.bird.commonform.svsearch.logic.SearchLogic;

/**
 * SV検索処理ロジック
 * @author kinugawa(ASPAC)
 */
public class SearchLogicImpl implements SearchLogic {

    public static final String LOGIC_ID = SvSearchAction.SCREEN_ID+"L04";
    /* UISvDao */
    private UISvDao uiSvDao;

    /**
     * SV検索Daoを取得します。
     * @return uiSvDao
     */
    public UISvDao getUISvDao() {
        return uiSvDao;
    }

    /**
     * SV検索Daoを設定します。
     * @param uiSvDao
     */
    public void setUISvDao(UISvDao uiSvDao) {
        this.uiSvDao = uiSvDao;
    }

    /**
     * SV検索処理
     */
	public List execute(SvSearchConditionDto svSearchConditionDto, List indexSearchList) {
        String sortType = null;
        String inEnd = null;

        if ( !svSearchConditionDto.getSortSeq().equals( "SV_CD" ) ) {
            sortType = new String();
        }
        String[] nameKna = (String[]) indexSearchList.toArray(new String[3]);
        return getUISvDao().select(
                svSearchConditionDto.getKaisyaCd(),
                svSearchConditionDto.getSibuCd(),
                (svSearchConditionDto.getOnerNameKj() == null) ? null : "%" + svSearchConditionDto.getOnerNameKj() + "%",
                svSearchConditionDto.getOnerCd(),
                (svSearchConditionDto.getMiseNameKj() == null) ? null : "%" + svSearchConditionDto.getMiseNameKj() + "%",
                svSearchConditionDto.getMiseCd(),
                (nameKna[0] == null) ? null : nameKna[0] + "%",
                (nameKna[1] == null) ? null : nameKna[1] + "%",
                (nameKna[2] == null) ? null : nameKna[2] + "%",
                sortType,
                inEnd);
	}
}
