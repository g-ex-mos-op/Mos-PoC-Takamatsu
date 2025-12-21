/**
 * 2008/06/23
 */
package jp.co.isid.mos.bird.storemanage.claimref.action.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.storemanage.claimref.action.ListAction;
import jp.co.isid.mos.bird.storemanage.claimref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.claimref.dto.SessionDto;
import jp.co.isid.mos.bird.storemanage.claimref.logic.ConditionLogic;
import jp.co.isid.mos.bird.storemanage.claimref.logic.SearchLogic;
import jp.co.isid.mos.bird.storemanage.claimref.util.VoiceRefUtil;
import jp.co.isid.mos.bird.storemanage.common.util.StoreManageUtil;

/**
 * 個店管理：『お客様の声』
 * 一覧（初期）画面アクション
 *
 * @author xkinu
 *
 */
public class ListActionImpl implements ListAction {
	private static final String ACTION_ID = VoiceRefUtil.SCREEN_ID+"A0";
    /* アクションID：初期処理(初期画面用) */
    public static final String initialize_ACTION_ID = ACTION_ID+"1";
    /* アクションID：オーナー検索処理 */
    public static final String callOnerForm_ACTION_ID = ACTION_ID+"2";
    /* アクションID：検索処理 */
    public static final String search_ACTION_ID = ACTION_ID+"2";
    /* アクションID：個店ポータル閲覧処理 */
    public static final String kotenPortal_ACTION_ID = ACTION_ID+"3";
    /* アクションID：詳細閲覧処理 */
    public static final String viewInfo_ACTION_ID = ACTION_ID+"4";

    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;

    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** DTO【自画面リクエスト用】*/
    private RequestDto voiceRefReqDto;
    /** DTO【自画面レンダリング用】*/
    private RequestDto voiceRefRenderedDto;
    /** DTO【自画面セッション用】*/
    private SessionDto voiceRefSesDto;
    /** DTO【自画面照会情報保持】*/
    private RequestDto voiceRefViewDto;

    /** DTO【オーナー選択】*/
    private OwnerSearchDto ownerSearchDto;
    /** LOGIC【条件項目取得】*/
    private ConditionLogic voiceRefConditionLogic;
    /** LOGIC【検索結果取得】*/
    private SearchLogic voiceRefSearchLogic;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.storemanage.claimref.action.ListAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行います。
        if(getPullDownMenuDto().isClearFlg()){
            //1．DTO【プルダウンメニュー】.クリアフラグをfalseに設定します。
            getPullDownMenuDto().setClearFlg(false);
            //2.【自画面共通】初期化処理
            VoiceRefUtil.initialize(getVoiceRefConditionLogic()
            		, getVoiceRefSesDto(), getVoiceRefReqDto()
            		, getBirdDateInfo(), getBirdUserInfo()
            		);
        }
        //２．処理１以外の場合下記の処理を行います。
        else {
            if((getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
                //1.【自画面共通】初期化処理
                VoiceRefUtil.initialize(getVoiceRefConditionLogic()
                		, getVoiceRefSesDto(), getVoiceRefReqDto()
                		, getBirdDateInfo(), getBirdUserInfo()
                		);
                //2.共通DTO【別画面遷移情報】.会社コードをDTO【自画面Request】.会社コードへ設定します。
                getVoiceRefReqDto().setCompanyCd(getCommonCodeDto().getCompanyCd());
                voiceRefRenderedDto.setCompanyCd(voiceRefReqDto.getCompanyCd());
                voiceRefRenderedDto.setConditionDataComp(voiceRefReqDto.isConditionDataComp());
                if(!CommonUtil.isNull(getCommonCodeDto().getNavigationCase())
                		&& StoreManageUtil.VIEW_ID_CLAIM_TOTAL.equals(getCommonCodeDto().getNavigationCase())) {
                	//分類別集計画面からの遷移の場合下記の処理を行います。
	                getVoiceRefReqDto().setHyojiNaiyo((String)getCommonCodeDto().getParam(StoreManageUtil.CLAIM_BIRDLINK_PARAM_TYPECD));
	                getVoiceRefReqDto().setTaishoNengetu((String)getCommonCodeDto().getParam(StoreManageUtil.CLAIM_BIRDLINK_PARAM_NENGETU));
	                getVoiceRefReqDto().setTaishoJoken((String)getCommonCodeDto().getParam(StoreManageUtil.CLAIM_BIRDLINK_PARAM_TAISHOJOKEN));
	                getVoiceRefReqDto().setHyojiTaisho((String)getCommonCodeDto().getParam(StoreManageUtil.CLAIM_BIRDLINK_PARAM_HYOJITAISHO));
	                getVoiceRefReqDto().setBnrM((String)getCommonCodeDto().getParam(StoreManageUtil.CLAIM_BIRDLINK_PARAM_BNRM));
                }
                else {
                	if(!CommonUtil.isNull(getCommonCodeDto().getOnerCd())){
                    	//オーナーポータル画面からの遷移の場合、下記の値をDTO【自画面Request】へ設定します。
		                getVoiceRefReqDto().setTaishoJoken(TaishoJoken.CODE_ONER);
		                getVoiceRefReqDto().setHyojiTaisho(getCommonCodeDto().getOnerCd());
                	}
                	else if(!CommonUtil.isNull(getCommonCodeDto().getMiseCd())){
                    	//個店ポータル画面からの遷移の場合、下記の値をDTO【自画面Request】へ設定します。
		                getVoiceRefReqDto().setTaishoJoken(TaishoJoken.CODE_MISE);
		                getVoiceRefReqDto().setHyojiTaisho(getCommonCodeDto().getMiseCd());
                	}
                }
                //3.BIRD内遷移フラグをリセット
            	getCommonCodeDto().clear();
               	//4.【自画面共通】検索処理を行います。
        		VoiceRefUtil.search(getVoiceRefSearchLogic(), getVoiceRefSesDto(), getVoiceRefReqDto());
            }
            else if (getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT)  {
                //ウィンドウIDセット
            	setVoiceRefReqDto(getVoiceRefSesDto().getRequestDto(getOwnerSearchDto().getWindowId()));
                if (getOwnerSearchDto().getReturnKind() == OwnerSearchDto.RETURNKIND_SELECT) {
                	getVoiceRefReqDto().setCompanyCd(getOwnerSearchDto().getCompanyCd());
                	getVoiceRefReqDto().setTaishoJoken(TaishoJoken.CODE_ONER);
                	getVoiceRefReqDto().setHyojiTaisho(getOwnerSearchDto().getOnerCd());
                }
                //オーナー選択画面情報をクリアします。
                getOwnerSearchDto().clear();
            }
        	//1.【自画面共通】表示検索データ設定処理
        	VoiceRefUtil.setView(getVoiceRefSearchLogic()
        			, getVoiceRefSesDto()
        			, getVoiceRefReqDto()
        			, getVoiceRefViewDto());
        }
        voiceRefRenderedDto.setCompanyCd(voiceRefReqDto.getCompanyCd());
        voiceRefRenderedDto.setConditionDataComp(voiceRefReqDto.isConditionDataComp());
        //３．Nullをリターンします。
        return null;
	}
    /**
     * オーナー検索フォーム呼び出し処理
     * @return オーナー検索フォームViewID
     */
    public String callOnerForm() {

	    //４．DTO【自画面Session】へ処検索済み条件値を設定する。
		getVoiceRefSesDto().setRequestDto(getVoiceRefReqDto().getWindowId(), getVoiceRefReqDto());
        //遷移元情報を設定
        getOwnerSearchDto().setCompanyCd(getVoiceRefReqDto().getCompanyCd());
        getOwnerSearchDto().setNavigationCase(VoiceRefUtil.VIEW_ID_LIST);
        //初期化
        getOwnerSearchDto().setInitFlag(true);
        getOwnerSearchDto().setWindowId(getVoiceRefReqDto().getWindowId());
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getVoiceRefReqDto().getCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        getOwnerSearchDto().setNeedReturnKind(true);

        return CommonUtil.VIEW_ID_OWNERSEARCH;
    }

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.storemanage.claimref.action.ListAction#search()
	 */
	public String search() {
       	//１．【自画面共通】検索処理を行います。
		VoiceRefUtil.search(getVoiceRefSearchLogic(), getVoiceRefSesDto(), getVoiceRefReqDto());
		//３．Nullをリターンします。
		return null;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.storemanage.claimref.action.ListAction#kotenPortal()
	 */
	public String kotenPortal() {
        CommonCodeDto commonCodeDto = getCommonCodeDto();
        if (commonCodeDto == null) {
            commonCodeDto = new CommonCodeDto();
        }
		getCommonCodeDto().setCompanyCd(getVoiceRefViewDto().getCompanyCd());
		getCommonCodeDto().setMiseCd(getVoiceRefViewDto().getMiseCd());
		//個店ポータル照会画面IDをリターンします。
		return CommonUtil.VIEW_ID_MISEREF;
	}

	/**
	 *
	 * @see jp.co.isid.mos.bird.storemanage.claimref.action.ListAction#viewInfo()
	 */
    //受付番号リンク機能廃止に伴い、この処理使いません。(たぶん)
	public String viewInfo() {
        //１．DTO【自画面Session】.ウィンドウID更新処理で新しいWindowIDを生成します。
        int windowId = getVoiceRefSesDto().updateWindowid();
        //２．DTO【自画面Request】.ウィンドウIDへ処理1で生成した新しいWindowIDを設定します。
        getVoiceRefViewDto().setWindowId(windowId);
        //３．DTO【自画面Request】.別ウィンドウオープン判断フラグにfalseを設定します。
        getVoiceRefReqDto().setNewWindowFlg(false);
        //４．DTO【自画面リクエスト用】.画面ViewIDへ店舗一覧画面VIEWIDを設定します。
		getVoiceRefViewDto().setViewId(VoiceRefUtil.VIEW_ID_INFO);
       	//５．【自画面共通】検索処理を行います。
		List listSearch = getVoiceRefSesDto().getListSearchData(getVoiceRefReqDto().getWindowId());
		getVoiceRefViewDto().setViewInfo(VoiceRefUtil.getInfoData(listSearch, getVoiceRefViewDto().getKanriNo()));

		//７．詳細画面IDをリターンします。
		return VoiceRefUtil.VIEW_ID_INFO;
	}

	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo 設定する birdDateInfo。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	/**
	 * @param birdUserInfo 設定する birdUserInfo。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	/**
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param pullDownMenuDto 設定する pullDownMenuDto。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}
	/**
	 * 共通DTO【別画面遷移情報】取得処理
	 *
	 * BIRD間の遷移時使用情報保持DTO取得処理
	 * @return
	 */
    public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }

	/**
	 * @return voiceRefConditionLogic を戻します。
	 */
	public ConditionLogic getVoiceRefConditionLogic() {
		return voiceRefConditionLogic;
	}

	/**
	 * @param voiceRefConditionLogic 設定する voiceRefConditionLogic。
	 */
	public void setVoiceRefConditionLogic(ConditionLogic voiceRefConditionLogic) {
		this.voiceRefConditionLogic = voiceRefConditionLogic;
	}

	/**
	 * @return voiceRefReqDto を戻します。
	 */
	public RequestDto getVoiceRefReqDto() {
		return voiceRefReqDto;
	}

	/**
	 * @param voiceRefReqDto 設定する voiceRefReqDto。
	 */
	public void setVoiceRefReqDto(RequestDto voiceRefReqDto) {
		this.voiceRefReqDto = voiceRefReqDto;
	}

	/**
	 * @return voiceRefSearchLogic を戻します。
	 */
	public SearchLogic getVoiceRefSearchLogic() {
		return voiceRefSearchLogic;
	}

	/**
	 * @param voiceRefSearchLogic 設定する voiceRefSearchLogic。
	 */
	public void setVoiceRefSearchLogic(SearchLogic voiceRefSearchLogic) {
		this.voiceRefSearchLogic = voiceRefSearchLogic;
	}

	/**
	 * @return voiceRefSesDto を戻します。
	 */
	public SessionDto getVoiceRefSesDto() {
		return voiceRefSesDto;
	}

	/**
	 * @param voiceRefSesDto 設定する voiceRefSesDto。
	 */
	public void setVoiceRefSesDto(SessionDto voiceRefSesDto) {
		this.voiceRefSesDto = voiceRefSesDto;
	}

	/**
	 * @return voiceRefViewDto を戻します。
	 */
	public RequestDto getVoiceRefViewDto() {
		return voiceRefViewDto;
	}

	/**
	 * @param voiceRefViewDto 設定する voiceRefViewDto。
	 */
	public void setVoiceRefViewDto(RequestDto voiceRefViewDto) {
		this.voiceRefViewDto = voiceRefViewDto;
	}
	/**
	 * @return ownerSearchDto を戻します。
	 */
	public OwnerSearchDto getOwnerSearchDto() {
		return ownerSearchDto;
	}
	/**
	 * @param ownerSearchDto 設定する ownerSearchDto。
	 */
	public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
		this.ownerSearchDto = ownerSearchDto;
	}
	public RequestDto getVoiceRefRenderedDto() {
		return voiceRefRenderedDto;
	}
	public void setVoiceRefRenderedDto(RequestDto voiceRefRenderedDto) {
		this.voiceRefRenderedDto = voiceRefRenderedDto;
	}

}
