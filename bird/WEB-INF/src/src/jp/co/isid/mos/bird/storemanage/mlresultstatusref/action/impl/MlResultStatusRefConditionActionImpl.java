/*
 * 作成日: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.action.MlResultStatusRefConditionAction;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.EntryYearInfoDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.MlResultStatusRefDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.entity.UIMLResultNum;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.entity.UITaishoKenshuInfo;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.logic.GetConditionLogic;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.logic.GetResultLogic;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.util.PrivateUtil;

/**
 * マスターライセンス結果状況確認画面
 * 初期画面アクション
 * 
 * @author Noh
 */
public class MlResultStatusRefConditionActionImpl extends CsvOutput2ActionImpl
	implements	MlResultStatusRefConditionAction
{
	
	/* アクションID */
	public static final String initialize_ACTION_ID  = "BSM007A01";
	public static final String result_ACTION_ID      = "BSM007A02";
	public static final String downloadCsv_ACTION_ID = "BSM007A03";
	public static final String onerSearch_ACTION_ID  = "BSM007A04";
	public static final String miseSrearch_ACTION_ID = "BSM007A05";
	
	/** DTO【自画面リクエスト情報】*/
	private RequestDto mlresultstatusrefRequestDto; 
	/** DTO【自画面セッション情報】*/
	private MlResultStatusRefDto mlResultStatusRefDto; 
	/* 店舗選択 */
	private MiseSearchDto miseSearchDto;
	/* オーナー選択 */
	private OwnerSearchDto ownerSearchDto;
	/* BIRDユーザ情報 */
	private BirdUserInfo birdUserInfo;
	/* BIRD日付情報 */
	private BirdDateInfo birdDateInfo;
	/* 初期表示情報の検索 */
	private GetConditionLogic getConditionLogic;
	/* マスターライセンス結果状況取得 */
	private GetResultLogic getResultLogic;
	
    /* pullDownMenuDto */
    private PullDownMenuDto pullDownMenuDto;

	
	/* 初期処理 */
	public String initialize() {
		/* 初期化 */
		if(pullDownMenuDto.isClearFlg()){
			/* 初期表示情報の検索 */
			getGetConditionLogic().execute(getBirdUserInfo(), getBirdDateInfo(), mlResultStatusRefDto);
			
			getMlresultstatusrefRequestDto().setSelectFlg(PrivateUtil.TAISHO_JOKEN_ALL);//全て
			getMlresultstatusrefRequestDto().setEntryFlg(PrivateUtil.DL_TYPE_NENDOKAI);
			
			pullDownMenuDto.setClearFlg(false);
		}
		else {
	        // １．オーナー選択画面から遷移された場合、下記の処理を行う。
	        // オーナー検索画面からの戻り(オーナー決定・戻るボタン)
	        if (getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT)  {
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
				getMlresultstatusrefRequestDto().setSelectFlg(PrivateUtil.TAISHO_JOKEN_MISE);
				/* 店情報取得 */
				if (getMiseSearchDto().isActionFlg()) {
					getMlresultstatusrefRequestDto().setMiseCd(getMiseSearchDto().getMiseCd());
					getMiseSearchDto().setActionFlg(false);
				}
				getMiseSearchDto().clear();
	        }
		}
				
		return null;
	}
	
	/* 検索 */
	public String result(){
		String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
		/* 検索条件セット */
		if(userTypeCd.equals(UserType.ONER)){
			getMlresultstatusrefRequestDto().setCompanyCd(mlResultStatusRefDto.getCompanyCd());
			getMlresultstatusrefRequestDto().setSelectFlg(PrivateUtil.TAISHO_JOKEN_OENR); // TODO SQL起動用
			getMlresultstatusrefRequestDto().setOnerCd(getUserOnerCd());
		}

		/* 対象試験セット */
		int entryFlg = getMlresultstatusrefRequestDto().getEntryFlg();

		// 対象試験選択判定
		if (entryFlg == PrivateUtil.DL_TYPE_NENDOKAI) {
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
		
		// TODO 結果画面検索条件表示設定
		mlResultStatusRefDto.setSelectViewMode(0);
		
		return PrivateUtil.result_VIEW_ID;
	}
	
	/**
	 *  一括ダウンロード処理
	 */
	public void downloadCsv() throws IOException, ApplicationException {
		String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
		/* 検索条件セット */
		if(userTypeCd.equals(UserType.ONER)){
			getMlresultstatusrefRequestDto().setCompanyCd(mlResultStatusRefDto.getCompanyCd());
			getMlresultstatusrefRequestDto().setSelectFlg(PrivateUtil.TAISHO_JOKEN_OENR); // TODO SQL起動用
			getMlresultstatusrefRequestDto().setOnerCd(getUserOnerCd());
		}
		getMlresultstatusrefRequestDto().setEntryFlg(PrivateUtil.DL_TYPE_IKKATU);
		getMlresultstatusrefRequestDto().setBandleYear1(mlResultStatusRefDto.getBandleYear1());
		getMlresultstatusrefRequestDto().setBandleYear2(mlResultStatusRefDto.getBandleYear2());
		getMlresultstatusrefRequestDto().setBandleYear3(mlResultStatusRefDto.getBandleYear3());

		/* マスターライセンス結果状況取得 */
		getGetResultLogic().execute(getBirdUserInfo(), getBirdDateInfo(), getMlresultstatusrefRequestDto());
		if(getMlresultstatusrefRequestDto().getListStaff() == null || getMlresultstatusrefRequestDto().getListStaff().size() == 0){
			mlResultStatusRefDto.clearSearchData();
			throw new NoResultException("");
		}
		//検索結果＆条件をセッションに格納します。
		getMlresultstatusrefRequestDto().settingSearchData(mlResultStatusRefDto);
		yearKubun();
		super.downloadCsv();
	}
	
	/* オーナー選択処理 */
	public String onerSearch(){
		getOwnerSearchDto().clear();
		getOwnerSearchDto().setInitFlag(true);
		getOwnerSearchDto().setNeedReturnKind(true);
		getOwnerSearchDto().setNavigationCase(PrivateUtil.condition_VIEW_ID);
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
		getMiseSearchDto().setNavigationCase(PrivateUtil.condition_VIEW_ID);
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
	/* Sessionのユーザ対応オーナー.管理会社企業コード='00'のオーナーコード */
	private String getUserOnerCd(){
		String s_onerCd = null;
		for (Iterator it = birdUserInfo.getUserOner().iterator(); it.hasNext();) {
			UIUserOner uiUserOner = (UIUserOner) it.next();
			if (uiUserOner.getCompanyCd().equals("00")) {
				s_onerCd = uiUserOner.getOnerCd();
			}
		}
		return s_onerCd;
	}
	/**e
	 * @return getConditionLogic を戻します。
	 */
	public GetConditionLogic getGetConditionLogic() {
		return getConditionLogic;
	}
	/**
	 * @param getConditionLogic getConditionLogic を設定。
	 */
	public void setGetConditionLogic(GetConditionLogic getConditionLogic) {
		this.getConditionLogic = getConditionLogic;
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
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * @param birdUserInfo birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
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
	 * pullDownMenuDtoの設定
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}
	/**
	 * pullDownMenuDtoの設定
	 * @param pullDownMenuDto pullDownMenuDto を設定。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	public RequestDto getMlresultstatusrefRequestDto() {
		return mlresultstatusrefRequestDto;
	}

	public void setMlresultstatusrefRequestDto(
			RequestDto mlresultstatusrefRequestDto) {
		this.mlresultstatusrefRequestDto = mlresultstatusrefRequestDto;
	}
}
