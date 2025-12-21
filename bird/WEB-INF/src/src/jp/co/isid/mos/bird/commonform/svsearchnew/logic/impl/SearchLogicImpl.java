package jp.co.isid.mos.bird.commonform.svsearchnew.logic.impl;


import java.util.List;

import jp.co.isid.mos.bird.commonform.svsearchnew.common.SvSearchNewConst;
import jp.co.isid.mos.bird.commonform.svsearchnew.dao.UISvDao;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchConditionDto;
import jp.co.isid.mos.bird.commonform.svsearchnew.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;

/**
 * SV検索処理ロジック
 * @author kusama
 */
public class SearchLogicImpl implements SearchLogic {

    public static final String LOGIC_ID = SvSearchNewConst.VIEW_ID + "L03";
    /* UISvDao */
    private UISvDao newUISvDao;

    /**
     * SV検索Daoを取得します。
     * @return uiSvDao
     */
    public UISvDao getUISvDao() {
        return newUISvDao;
    }

    /**
     * SV検索Daoを設定します。
     * @param uiSvDao
     */
    public void setUISvDao(UISvDao uiSvDao) {
        this.newUISvDao = uiSvDao;
    }

    /**
     * SV検索処理
     */
	public List execute(SvSearchConditionDto svSearchConditionDto, List indexSearchList) {
        validate(svSearchConditionDto);
        
        String sortType = null;

        if ( !svSearchConditionDto.getSortSeq().equals( "SV_CD" ) ) {
            sortType = new String();
        }
        String[] nameKna = (String[]) indexSearchList.toArray(new String[4]);
        return getUISvDao().select(
                svSearchConditionDto.getCompanyCd(),
                svSearchConditionDto.getSibuCd(),
                (svSearchConditionDto.getOnerNameKj() == null) ? null : "%" + svSearchConditionDto.getOnerNameKj() + "%",
                svSearchConditionDto.getOnerCd(),
                (svSearchConditionDto.getMiseNameKj() == null) ? null : "%" + svSearchConditionDto.getMiseNameKj() + "%",
                svSearchConditionDto.getMiseCd(),
                (nameKna[0] == null) ? null : nameKna[0] + "%",
                (nameKna[1] == null) ? null : nameKna[1] + "%",
                (nameKna[2] == null) ? null : nameKna[2] + "%",
                (nameKna[3] == null) ? null : nameKna[3] + "%",
                sortType);
	}
    
    private void validate(SvSearchConditionDto dto) {
        if (!isNull(dto.getOnerCd())) {
            if (dto.getOnerCd().getBytes().length > 5) {
                throw new InvalidInputException("オーナーコード");
            }
        }
        if (!isNull(dto.getMiseCd())) {
            if (dto.getMiseCd().getBytes().length > 5) {
                throw new InvalidInputException("店コード");
            }
        }
    }
    
    private boolean isNull(String value) {
        return value == null || value.trim().equals("") ? true : false;
    }
}