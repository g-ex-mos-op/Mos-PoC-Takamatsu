/*
 * 作成日: 2006/04/21
 */
package jp.co.isid.mos.bird.storemanage.mlholderlist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storemanage.mlholderlist.dao.UIMLHolderDao;
import jp.co.isid.mos.bird.storemanage.mlholderlist.dto.MlHolderListDto;
import jp.co.isid.mos.bird.storemanage.mlholderlist.logic.MlHolderListLogic;
/**
 * @author lee
 */
public class MlHolderListLogicImpl implements MlHolderListLogic {
	
	/* ロジックID */
    public static final String LOGIC_ID = "BSM005L01";
	
	private UIMLHolderDao uIMLHolderDao;

    public void execute(MlHolderListDto dto) {
		
		/* 入力したデータチェック */
		valueCheck(dto); 
		List resultList = uIMLHolderDao.selectMLHoder(dto);
		dto.setResultList(resultList);
	}
	

	private void valueCheck(MlHolderListDto dto){
		CodeVerifier codeVerifier = new CodeVerifier();

		if(dto.getSelectFlg()==0){
			if(isNull(dto.getSitenCd())){
				throw new NotNullException("支部","sitenS",null);
			}else{
				if(!codeVerifier.validate(dto.getSitenCd().trim())){
					throw new InvalidInputException("支部","sitenS",null);
				}
			}
		}
		if(dto.getSelectFlg()==1){
			if(isNull(dto.getOnerCd())){
				throw new NotNullException("オーナーコード","onerT",null);
			}else{
				if(!codeVerifier.validate(dto.getOnerCd().trim())){
					throw new InvalidInputException("オーナーコード","onerT",null);
				}
			}	
		}
		if(dto.getSelectFlg()==2){
			if(isNull(dto.getMiseCd())){
				throw new NotNullException("店舗コード","miseT",null);
			}else{
				if(!codeVerifier.validate(dto.getMiseCd().trim())){
					throw new InvalidInputException("店舗コード","miseT",null);
				}
			}
		}

		if(!isNull(dto.getOnerCd())){
			if(dto.getOnerCd().getBytes().length > 5){
				throw new InvalidInputException("オーナーコード","onerT",null);
			}
		}
		if(!isNull(dto.getMiseCd())){
			if(dto.getMiseCd().getBytes().length > 5){
				throw new InvalidInputException("店舗コード","miseT",null);
			}
		}
		dto.setSelectinitFlg(false);
		
	}
	/**
	 * nullチェック
	 */	
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
    }
	
	/**
	 * @return uIMLHolderDao を戻します。
	 */
	public UIMLHolderDao getUIMLHolderDao() {
		return uIMLHolderDao;
	}
	/**
	 * @param uIMLHolderDao を設定。
	 */
	public void setUIMLHolderDao(UIMLHolderDao uIMLHolderDao) {
		this.uIMLHolderDao = uIMLHolderDao;
	}
}
