package jp.co.isid.mos.bird.bizsupport.spot.logic.impl;


import jp.co.isid.mos.bird.bizsupport.spot.dao.CampResultDataDao;
import jp.co.isid.mos.bird.bizsupport.spot.dto.SpotResultDataDto;

import jp.co.isid.mos.bird.bizsupport.spot.entity.CmpResultData;
import jp.co.isid.mos.bird.bizsupport.spot.logic.CampResultDataLogic;

import jp.co.isid.mos.bird.framework.exception.NotNullException;


/**
 * キャンペーン情報取得ロジック
 *
 * @author xsong
 */
public class CampResultDataLogicImpl implements CampResultDataLogic {

	/**  ロジックID  */
    public static final String LOGIC_ID = "BBS028L02";

    /** キャンペーン受注管理情報Daoクラス */
    private CampResultDataDao cmpResultDataDao;
    
   
    
    /**
     * キャンペーン情報を取得します。
     * @param cmpNo キャンペーンNo
     * @param sibu　支部コード
     * @return  キャンペーン情報
     */
	public SpotResultDataDto execute(String cmpNo, String sibu) {
		
		// 入力チェック
		validate(cmpNo, sibu);
		
		//キャンペーン受注管理情報を取得
		CmpResultData data = this.getCmpResultDataDao().getCampResultData(cmpNo, sibu);
		
		if(data != null) {
		
			SpotResultDataDto spotResultDataDto = new SpotResultDataDto();	 
			spotResultDataDto.setCmpStartDate(data.getCmpFrom());
			spotResultDataDto.setCpmEndDate(data.getCmpTo());
			spotResultDataDto.setPosEndDate(data.getPosEndDt());
			spotResultDataDto.setPosStartDate(data.getPosFromDt());
			spotResultDataDto.setSakuseiDate(data.getSysDt());
			
			return spotResultDataDto;
		
		}
	
        return null;
	}
         		
		
	/**
	 * キャンペーン受注管理情報Daoを取得します。
	 * @return　cmpResultDataDao　キャンペーン受注管理情報Dao
	 */
    public CampResultDataDao getCmpResultDataDao() {
		return cmpResultDataDao;
	}

    /**
     * キャンペーン受注管理情報Daoを設定します。
     * @param cmpResultDataDao　キャンペーン受注管理情報Dao
     */
	public void setCmpResultDataDao(CampResultDataDao cmpResultDataDao) {
		this.cmpResultDataDao = cmpResultDataDao;
	}

	/**
	 * 入力チェック
	 * @param cmpNo　キャンペーンNo
	 * @param sibu　 支部コード
	 */
	private void validate(String cmpNo, String sibu) {

        if (cmpNo == null || cmpNo.trim().length() == 0) {
            throw new NotNullException("キャンペーンNO");
        }
        if (sibu == null || sibu.trim().length() == 0) {
        	throw new NotNullException("支部コード");
        }

    }

}