/*
 * 作成日: 2006/04/13
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.logic.impl;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dao.UIMLResultNumDao;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dao.UIMLResultStatusDao;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.logic.GetResultLogic;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.util.PrivateUtil;

/**
 * 結果情報取得処理ロジック
 * 
 * 更新日:209/09/08　xkinu　支部制限を検索結果取得時に設定するため下記の修正を行いました。
 *                          1.executeメソッドのパラメータMlResultStatusRefDtoをRequestDtoに変更しました。
 *                          2.executeメソッドにBIRDユーザ情報とBIRD日付情報を追加しました。
 * @author Noh
 */
public class GetResultLogicImpl implements GetResultLogic {
	
	/* ロジックID */
    public static final String LOGIC_ID = "BSM007L02";
	
	private UIMLResultStatusDao uIMLResultStatusDao;
	
	private UIMLResultNumDao uIMLResultNumDao;
	/**
	 * 
	 * 更新日:209/09/08　xkinu　支部制限を検索結果取得時に設定するため下記の修正を行いました。
	 *                          1.executeメソッドのパラメータMlResultStatusRefDtoをRequestDtoに変更しました。
	 *                          2.executeメソッドにBIRDユーザ情報とBIRD日付情報を追加しました。
	 */
	public void execute(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo, RequestDto dto) {
		
		/* 入力したデータチェック */
		valueCheck(dto); 
		String sysDate = birdDateInfo.getSysDate();
		String userId = birdUserInfo.getUserID();
		String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
		/* マスタライセンス結果状況情報 */
		dto.setListStaff(
				getUIMLResultStatusDao().select(sysDate
				, userId, userTypeCd, birdUserInfo.isLimit()
				, dto));
		
		/* マスタライセンス結果人数情報 */
		dto.setListStaffCnt(
				getUIMLResultNumDao().select(sysDate
				, userId, userTypeCd
				, dto));
		

	}
	/**
	 * 入力チェック処理
	 * 
	 * 更新日:2009/09/08 xkinu 一括ダウンロードの場合はチェック処理を実行しない処理を追加。
	 * @param dto
	 */
	private void valueCheck(RequestDto dto){
		CodeVerifier codeVerifier = new CodeVerifier();
		//一括ダウンロードの場合はチェック処理を実行しない。
		if(PrivateUtil.DL_TYPE_IKKATU == dto.getEntryFlg()) {
			return;
		}
		/* nullとコード欄 半角数字チェック */
		if(dto.getSelectFlg()==0){
			if(isNull(dto.getAreaDai())){
				throw new NotNullException("支部","areaDai", 0);
			}else{
				if(!codeVerifier.validate(dto.getAreaDai().trim())){
					throw new InvalidInputException("支部","areaDai", 0);
				}
			}
		}
		if(dto.getSelectFlg()==1){
			if(isNull(dto.getOnerCd())){
				throw new NotNullException("オーナーコード","onerT", 0);
			}else{
				if(!codeVerifier.validate(dto.getOnerCd().trim())){
					throw new InvalidInputException("オーナーコード","onerT", 0);
				}
			}	
		}
		if(dto.getSelectFlg()==2){
			if(isNull(dto.getMiseCd())){
				throw new NotNullException("店舗コード","miseT", 0);
			}else{
				if(!codeVerifier.validate(dto.getMiseCd().trim())){
					throw new InvalidInputException("店舗コード","miseT", 0);
				}
			}
		}
		/* 文字列長チェック */
		if(!isNull(dto.getOnerCd())){
			if(dto.getOnerCd().getBytes().length > 5){
				throw new InvalidInputException("オーナーコード","onerT", 0);
			}
		}
		if(!isNull(dto.getMiseCd())){
			if(dto.getMiseCd().getBytes().length > 5){
				throw new InvalidInputException("店舗コード","miseT", 0);
			}
		}
	
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
	 * @return uIMLResultNumDao を戻します。
	 */
	public UIMLResultNumDao getUIMLResultNumDao() {
		return uIMLResultNumDao;
	}
	/**
	 * @param resultNumDao uIMLResultNumDao を設定。
	 */
	public void setUIMLResultNumDao(UIMLResultNumDao resultNumDao) {
		uIMLResultNumDao = resultNumDao;
	}
	/**
	 * @return uIMLResultStatusDao を戻します。
	 */
	public UIMLResultStatusDao getUIMLResultStatusDao() {
		return uIMLResultStatusDao;
	}
	/**
	 * @param resultStatusDao uIMLResultStatusDao を設定。
	 */
	public void setUIMLResultStatusDao(UIMLResultStatusDao resultStatusDao) {
		uIMLResultStatusDao = resultStatusDao;
	}
}
