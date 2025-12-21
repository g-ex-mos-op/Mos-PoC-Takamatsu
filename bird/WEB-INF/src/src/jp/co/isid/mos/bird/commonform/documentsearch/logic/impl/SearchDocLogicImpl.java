/*
 * 作成日: 2006/01/20
 *
 */
package jp.co.isid.mos.bird.commonform.documentsearch.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.commonform.documentsearch.dao.UIDocSearchDao;
import jp.co.isid.mos.bird.commonform.documentsearch.dto.DocumentSearchConditionDto;
import jp.co.isid.mos.bird.commonform.documentsearch.logic.SearchDocLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 関連文書取得
 * 
 * @author m.onodera
 *
 */
public class SearchDocLogicImpl implements SearchDocLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BCO001L01";
    
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;
    
	/**
	 * 関連文書情報DAO
	 */
	private UIDocSearchDao uIDocSearchDao;

	/**
	 * 実行処理
	 * 
	 * @param dto
	 * @param userInfo
	 * @param dateInfo
	 * @return
	 */
	public List execute(DocumentSearchConditionDto dto ,BirdUserInfo birdUserInfo, BirdDateInfo dateInfo) {
	    List data = null;
	    
		int limit = dto.getMaxPageCount();
		int offset = dto.getPageFirstRecordNumber();
		String infoShu = dto.getSelectInfoShu();
		String cateId = dto.getSelectCateId();
		String subCateId = dto.getSelectSubCateId();
		String title = (dto.getSelectTitle() == null) ? null : "%" + dto.getSelectTitle() + "%";
		String date = (dto.getSelectDate() == null) ? null : dto.getSelectDate() + "%";
	    String sysDate = dateInfo.getSysDate();
	    int startRowNo = offset + 1;
	    int endRowNo = offset + limit;
	    
	    if(infoShu.equals("02")){
	    	//通達の場合
		    data = getUIDocumentSearchDao().selectNtcm(
		    		  infoShu, cateId, subCateId, title, date
		    		, startRowNo, endRowNo);
	    }
	    else if(infoShu.equals("03")){
	    	//文書の場合
			data = getUIDocumentSearchDao().selectDocm(
					  sysDate
		    		, infoShu, cateId, subCateId, title, date
			    	, startRowNo, endRowNo);
	    }
	    else if(infoShu.equals("04")){
	    	//フォームの場合
			data = getUIDocumentSearchDao().selectFrmm(
					  sysDate
					, infoShu, cateId, subCateId, title, date
					, startRowNo, endRowNo);
	    }
        
        List returnData = new ArrayList();
        for ( int i = 0 ; i < data.size(); i ++ ) {
            UIDocSearch entity = (UIDocSearch)data.get(i);
            if ( getKanrenBunshoInfoLogic.checkBunshoAccess( entity,birdUserInfo,new String()) ) {
                returnData.add( entity );
            }
        }
		return returnData ;
	}

	public GetKanrenBunshoInfoLogic getGetKanrenBunshoInfoLogic() {
        return getKanrenBunshoInfoLogic;
    }

    public void setGetKanrenBunshoInfoLogic(
            GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic) {
        this.getKanrenBunshoInfoLogic = getKanrenBunshoInfoLogic;
    }
	/**
     * 関連文書情報DAOを取得します。
     * 
     * @return 関連文書情報DAO
     */
	public UIDocSearchDao getUIDocumentSearchDao() {
		return uIDocSearchDao;
	}

    /**
     * 関連文書情報DAOを設定します。
     * 
     * @param uIUserRoleDao 関連文書情報DAO
     */
	public void setUIDocumentSearchDao(UIDocSearchDao uIDocSearchDao) {
		this.uIDocSearchDao = uIDocSearchDao;
	}

}
