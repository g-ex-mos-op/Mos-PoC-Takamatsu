package jp.co.isid.mos.bird.bizsupport.spot.logic.impl;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.spot.dao.CampResultTableDao;
import jp.co.isid.mos.bird.bizsupport.spot.dto.SpotResultDataDto;
import jp.co.isid.mos.bird.bizsupport.spot.entity.TenpoResult;
import jp.co.isid.mos.bird.bizsupport.spot.logic.TenpoDataLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;



/**
 * スポット未受注店情報取得ロジック
 * 
 * @author xsong
 */

public class TenpoDataLogicImpl implements TenpoDataLogic {

	/**  ロジックID  */
    public static final String LOGIC_ID = "BBS028L03";

    /** スポット未受注店情報Daoクラス */
    private CampResultTableDao cmpResultTableDao;
    
       
    /**
     * スポット未受注店情報を取得します。
     * @param cmpNo キャンペーンNo
     * @param sibu　支部コード
     * @return returnList　スポット未受注店情報List
     */
	public List execute(String cmpNo, String sibu) {
		
		//入力チェック
		validate(cmpNo, sibu);
		
		List returnList = new ArrayList();
		
		//スポット未受注店情報を取得
		List resultList = this.getCmpResultTableDao().getCampResultTableData(sibu, cmpNo);
		
		if(resultList != null || !resultList.isEmpty()) {
	
			for (Iterator it = resultList.iterator(); it.hasNext();) {
				
				SpotResultDataDto spotResultDataDto = new SpotResultDataDto();      
				TenpoResult ct = ((TenpoResult)it.next());
				  	 
				spotResultDataDto.setFlg1(ct.getShuErrFlg());
				spotResultDataDto.setTenName1(ct.getMiseCode()+ "　" + ct.getMiseNameKj());
				
				if(it.hasNext()) {
				TenpoResult ct2 = ((TenpoResult)it.next());
			  	 
				spotResultDataDto.setFlg2(ct2.getShuErrFlg());
				spotResultDataDto.setTenName2(ct2.getMiseCode()+ "　" + ct2.getMiseNameKj());
				
				} else {
					
					spotResultDataDto.setFlg2("");
					spotResultDataDto.setTenName2("");
				}
			
				if(it.hasNext()) {
					
					TenpoResult ct3 = ((TenpoResult)it.next());
				  	 
					spotResultDataDto.setFlg3(ct3.getShuErrFlg());
					spotResultDataDto.setTenName3(ct3.getMiseCode()+ "　" + ct3.getMiseNameKj());
				} else {
					spotResultDataDto.setFlg3("");
					spotResultDataDto.setTenName3("");
				}
							
				returnList.add(spotResultDataDto);
				
		   }
		}
		
        return returnList;
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
	
	/**
	 * スポット未受注店情報を取得します。
	 * @return　cmpResultTableDao　スポット未受注店情報
	 */
	public CampResultTableDao getCmpResultTableDao() {
		return cmpResultTableDao;
	}

    
	/**
	 * スポット未受注店情報を設定します。
	 * @param cmpResultTableDao　スポット未受注店情報
	 */
	public void setCmpResultTableDao(CampResultTableDao cmpResultTableDao) {
		this.cmpResultTableDao = cmpResultTableDao;
	}


}