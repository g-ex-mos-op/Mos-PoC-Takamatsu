/*
 * çÏê¨ì˙: 2005/12/22
 *
 */
package jp.co.isid.mos.bird.commonform.misesearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.common.util.IndexSearchUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dao.UIGroupTogoMiseDao;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchConditionDto;
import jp.co.isid.mos.bird.commonform.misesearch.logic.SearchMiseLogic;

/**
 * @author xyuchida
 *
 */
public class SearchMiseLogicImpl implements SearchMiseLogic {

    public static final String LOGIC_ID = "BCO008L01";

    private UIGroupTogoMiseDao uiGroupTogoMiseDao;

    public UIGroupTogoMiseDao getUiGroupTogoMiseDao() {
        return uiGroupTogoMiseDao;
    }

    public void setUiGroupTogoMiseDao(UIGroupTogoMiseDao uiGroupTogoMiseDao) {
        this.uiGroupTogoMiseDao = uiGroupTogoMiseDao;
    }

	public List execute(MiseSearchConditionDto miseSearchConditionDto) {

		// 50âπåüçıï∂éöÉäÉXÉgéÊìæ
        String[] miseNameKna = (String[]) (IndexSearchUtil.getIndexSearchKeyList(miseSearchConditionDto.getIndexSearchKey())).toArray(new String[4]);

// add start xkhata 2006/05/18 èåèí«â¡ëŒâû
        String inClose = null;
        String closeType = "inClose";
        
        if ( closeType.equals( miseSearchConditionDto.getInClose() ) ) {
            inClose = new String();
        }
        String sortSeq = null;
        String sortType = "MISE_CD";
        
        if ( sortType.equals( miseSearchConditionDto.getSortSeq() ) ) {
            sortSeq = new String();
        }
// end
        // åüçıé¿çs
        return getUiGroupTogoMiseDao().select(
				miseSearchConditionDto.getRCompanyCd(),
				miseSearchConditionDto.getSibuCd(),
                (miseSearchConditionDto.getOnerNameKj() == null) ? null : "%" + miseSearchConditionDto.getOnerNameKj() + "%",
                miseSearchConditionDto.getOnerCd(),
                (miseSearchConditionDto.getMiseNameKj() == null) ? null : "%" + miseSearchConditionDto.getMiseNameKj() + "%",
                miseSearchConditionDto.getMiseCd(),
				(miseNameKna[0] == null) ? null : miseNameKna[0] + "%",
				(miseNameKna[1] == null) ? null : miseNameKna[1] + "%",
				(miseNameKna[2] == null) ? null : miseNameKna[2] + "%",
                (miseNameKna[3] == null) ? null : miseNameKna[3] + "%",
// add start xkhata 2006/05/18 èåèí«â¡ëŒâû
                 sortSeq,
                 inClose
// end
                 );
	}
}
