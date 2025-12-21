/*
 * 作成日: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.action.MlResultStatusRefAction;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.EntryYearInfoDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.MlResultStatusRefDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.entity.UIMLResultNum;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.entity.UITaishoKenshuInfo;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.logic.GetResultLogic;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.util.PrivateUtil;

/**
 * マスターライセンス結果状況確認画面
 * 結果画面アクション
 * 
 * @author Noh
 */
public class MlResultStatusRefActionImpl extends CsvOutput2ActionImpl implements MlResultStatusRefAction {
	
	/* アクションID */
	public static final String initialize_ACTION_ID  = "BSM007A06";
	public static final String result_ACTION_ID      = "BSM007A07";
	public static final String back_ACTION_ID        = "BSM007A08";
	public static final String onerSearch_ACTION_ID  = "BSM007A09";
	public static final String miseSrearch_ACTION_ID = "BSM007A10";
	
	/** DTO【自画面リクエスト】*/
	private RequestDto mlresultstatusrefRequestDto; 
	private MlResultStatusRefDto mlResultStatusRefDto;
	/* BIRDユーザ情報 */
	private BirdUserInfo birdUserInfo;
	/* BIRD日付情報 */
	private BirdDateInfo birdDateInfo;
	/* 店舗選択 */
	private MiseSearchDto miseSearchDto;
	/* オーナー選択 */
	private OwnerSearchDto ownerSearchDto;
	/* LOGIC【マスターライセンス結果状況取得】 */
	private GetResultLogic getResultLogic;

	/* 初期処理 */
	public String initialize() {
		
        // １．オーナー選択画面から遷移された場合、下記の処理を行う。
        // オーナー検索画面からの戻り(オーナー決定・戻るボタン)
        if (getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT)  {
			getMlresultstatusrefRequestDto().setSelectViewMode(PrivateUtil.JOKEN_VIEW_ON);
			getMlresultstatusrefRequestDto().setSelectFlg(PrivateUtil.TAISHO_JOKEN_OENR);
			/*オーナーコード取得*/
			if(getOwnerSearchDto().isActionFlag()){
				getMlresultstatusrefRequestDto().setOnerCd(getOwnerSearchDto().getOnerCd());
				getOwnerSearchDto().setActionFlag(false);
			}
			getOwnerSearchDto().clear();
        }
        // ２．店舗選択画面から遷移された場合、下記の処理を行う。
        // 店検索画面からの戻り(店決定・戻るボタン)
        if (getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT)  {
			getMlresultstatusrefRequestDto().setSelectViewMode(PrivateUtil.JOKEN_VIEW_ON);
			getMlresultstatusrefRequestDto().setSelectFlg(PrivateUtil.TAISHO_JOKEN_MISE);
			/* 店情報取得 */
			if (getMiseSearchDto().isActionFlg()) {
				getMlresultstatusrefRequestDto().setMiseCd(getMiseSearchDto().getMiseCd());
				getMiseSearchDto().setActionFlg(false);
			}
			getMiseSearchDto().clear();
        }
		return null;
	}
	
	/* 検索 */
	public String result(){
		
		/* 対象試験セット */
		int entryFlg = getMlresultstatusrefRequestDto().getEntryFlg();
		// 対象試験選択判定
		if (entryFlg == PrivateUtil.DL_TYPE_NENDOKAI) {
			getMlresultstatusrefRequestDto().setCompanyCd(mlResultStatusRefDto.getCompanyCd());
			getMlresultstatusrefRequestDto().setEntryYear(mlResultStatusRefDto.getLatestEntryYear());
			getMlresultstatusrefRequestDto().setEntryKai(mlResultStatusRefDto.getLatestEntryKai());
		}
		/* マスターライセンス結果状況取得 */
		getGetResultLogic().execute(getBirdUserInfo(), getBirdDateInfo(), getMlresultstatusrefRequestDto());
		if(getMlresultstatusrefRequestDto().getListStaff() == null || getMlresultstatusrefRequestDto().getListStaff().size() == 0){
			mlResultStatusRefDto.clearSearchData();
			throw new NoResultException("");
		}
		//検索結果＆条件をセッションに格納します。
		getMlresultstatusrefRequestDto().settingSearchData(mlResultStatusRefDto);
		yearKubun();
		return PrivateUtil.result_VIEW_ID;
	}

	
	/* 戻る */
	public String back(){
		return PrivateUtil.condition_VIEW_ID;
		
	}
	/* オーナー選択処理 */
	public String onerSearch(){
		getOwnerSearchDto().clear();
		getOwnerSearchDto().setInitFlag(true);
		getOwnerSearchDto().setNeedReturnKind(true);
		getOwnerSearchDto().setNavigationCase(PrivateUtil.result_VIEW_ID);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMlresultstatusrefRequestDto().getCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
		return PrivateUtil.onerSearch_VIEW_ID;
	}
	
	/* 店舗選択処理 */
	public String miseSrearch(){
		getMiseSearchDto().setActionFlg(true);
		getMiseSearchDto().setInitialFlag(true);
		getMiseSearchDto().setNeedReturnKind(true);
		getMiseSearchDto().setNavigationCase(PrivateUtil.result_VIEW_ID);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMlresultstatusrefRequestDto().getCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
		return PrivateUtil.miseSearch_VIEW_ID;
	}
	/* 実施時期設定 */
	private void yearKubun(){
		List babo = new ArrayList();
		for(int i=0 ; i<mlResultStatusRefDto.getResultNumList().size() ; i++){
			UIMLResultNum unit1 = (UIMLResultNum) mlResultStatusRefDto.getResultNumList().get(i);
			for(int j=0 ; j<mlResultStatusRefDto.getEntryList().size() ; j++){
				UITaishoKenshuInfo unit2 = (UITaishoKenshuInfo) mlResultStatusRefDto.getEntryList().get(j);
				if(unit1.getTotalLastYear().equals(unit2.getEntryYear())){
					if(unit1.getTotalLastKai().equals(unit2.getEntryKai())){
						EntryYearInfoDto newUnit = new EntryYearInfoDto();
						newUnit.setEntryYear(unit1.getTotalLastYear());
						newUnit.setEntryKai(unit1.getTotalLastKai());
						newUnit.setFromDt(unit2.getFromDt());
						newUnit.setToDt(unit2.getToDt());
						newUnit.setGokaku(unit1.getGoukakuNum());
						newUnit.setHugokaku(unit1.getFugoukakuNum());
                        newUnit.setHoryu(unit1.getHoryuNum());
                        newUnit.setMukou(unit1.getMukouNum());
						babo.add(newUnit);
					}
				}
			}
		}

		mlResultStatusRefDto.setKikanList(babo);
	}
	/**
	 * @return getResultLogic を戻します。
	 */
	public GetResultLogic getGetResultLogic() {
		return getResultLogic;
	}
	/**
	 * @param getResultLogic getResultLogic を設定。
	 */
	public void setGetResultLogic(GetResultLogic getResultLogic) {
		this.getResultLogic = getResultLogic;
	}
	/**
	 * @return miseSearchDto を戻します。
	 */
	public MiseSearchDto getMiseSearchDto() {
		return miseSearchDto;
	}
	/**
	 * @param miseSearchDto miseSearchDto を設定。
	 */
	public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
		this.miseSearchDto = miseSearchDto;
	}
	/**
	 * @return ownerSearchDto を戻します。
	 */
	public OwnerSearchDto getOwnerSearchDto() {
		return ownerSearchDto;
	}
	/**
	 * @param ownerSearchDto ownerSearchDto を設定。
	 */
	public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
		this.ownerSearchDto = ownerSearchDto;
	}
	
	/**
	 * @return mlResultStatusRefDto を戻します。
	 */
	public MlResultStatusRefDto getMlResultStatusRefDto() {
		return mlResultStatusRefDto;
	}
	/**
	 * @param mlResultStatusRefDto mlResultStatusRefDto を設定。
	 */
	public void setMlResultStatusRefDto(
			MlResultStatusRefDto mlResultStatusRefDto) {
		this.mlResultStatusRefDto = mlResultStatusRefDto;
	}

	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	public RequestDto getMlresultstatusrefRequestDto() {
		return mlresultstatusrefRequestDto;
	}

	public void setMlresultstatusrefRequestDto(
			RequestDto mlresultstatusrefRequestDto) {
		this.mlresultstatusrefRequestDto = mlresultstatusrefRequestDto;
	}
}
