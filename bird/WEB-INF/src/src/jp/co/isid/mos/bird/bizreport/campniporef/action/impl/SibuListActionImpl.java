/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.action.impl;

import jp.co.isid.mos.bird.bizreport.campniporef.action.SibuListAction;
import jp.co.isid.mos.bird.bizreport.campniporef.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.logic.SetCampaignLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.logic.SetCompanyLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.util.CampNipoRefUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 支部一覧用アクション
 * 
 * 2008/10/15 xkinu タブ構成(対象日・期間合計タブ)の廃止
 * @author xkinu
 *
 */
public class SibuListActionImpl implements SibuListAction {
	private static final String ACTION_ID = CampNipoRefUtil.SCREEN_ID+"A0";
    /* アクションID：初期処理(初期画面用) */
    public static final String initialize_ACTION_ID = ACTION_ID+"1";
    /* アクションID：対象会社変更処理 */
    public static final String changeCompany_ACTION_ID = ACTION_ID+"2";
    /* アクションID：対象キャンペーン変更処理 */
    public static final String changeCamp_ACTION_ID = ACTION_ID+"3";
    /* アクションID：検索処理 */
    public static final String execute_ACTION_ID = ACTION_ID+"4";
    /* アクションID：店舗リンク処理 */
    public static final String linkUp_ACTION_ID = ACTION_ID+"5";

    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** DTO【自画面リクエスト用】*/
    private RequestNipoDto campNipoRefReqDto;
    /** DTO【自画面セッション用】*/
    private SessionNipoDto campNipoRefSesDto;
    /** DTO【自画面照会情報保持】*/
    private RequestNipoDto campNipoRefViewDto;
    /** LOGIC【条件項目取得】*/
    private ConditionLogic campNipoRefConditionLogic;
    /** LOGIC【会社条件項目情報設定】*/
    private SetCompanyLogic campNipoRefSetCompanyLogic;
    /** LOGIC【キャンペーン条件項目情報設定】*/
    private SetCampaignLogic campNipoRefSetCampaignLogic;
    /** LOGIC【検索結果取得】*/
    private SearchLogic campNipoRefSearchLogic;
    /**
	 * 初期化処理
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.action.SibuListAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行います。
        if(getPullDownMenuDto().isClearFlg()){
            //1．DTO【プルダウンメニュー】.クリアフラグをfalseに設定します。
            getPullDownMenuDto().setClearFlg(false);
            //2.DTO【自画面リクエスト用】.画面ViewIDへ一覧画面のVIEWIDを設定します。
        	if(UserType.HONBU.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
        		//ユーザータイプコードが”本部”の場合は支部一覧画面IDを設定します。
            	getCampNipoRefReqDto().setViewId(CampNipoRefUtil.VIEW_ID_SIBU);
        	}else {
        		//ユーザータイプコードが”本部”以外の場合は店舗一覧画面IDを設定します。
            	getCampNipoRefReqDto().setViewId(CampNipoRefUtil.VIEW_ID_MISE);
        	}
            //3．【自画面共通】初期化処理
            CampNipoRefUtil.initialize(getCampNipoRefConditionLogic()
            		, getCampNipoRefSesDto(), getCampNipoRefReqDto()
            		, getBirdDateInfo(), getBirdUserInfo()
            		);
            
        }
        else if((getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
        	//1.BIRD内遷移フラグをリセット
        	getCommonCodeDto().clear();
            //2.DTO【自画面リクエスト用】.画面ViewIDへ一覧画面のVIEWIDを設定します。
        	if(UserType.HONBU.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
        		//ユーザータイプコードが”本部”の場合は支部一覧画面IDを設定します。
            	getCampNipoRefReqDto().setViewId(CampNipoRefUtil.VIEW_ID_SIBU);
        	}else {
        		//ユーザータイプコードが”本部”以外の場合は店舗一覧画面IDを設定します。
            	getCampNipoRefReqDto().setViewId(CampNipoRefUtil.VIEW_ID_MISE);
        	}
            //3．【自画面共通】初期化処理
            CampNipoRefUtil.initialize(getCampNipoRefConditionLogic()
            		, getCampNipoRefSesDto(), getCampNipoRefReqDto()
            		, getBirdDateInfo(), getBirdUserInfo()
            		);
        }
        //２．処理１以外の場合下記の処理を行います。
        else {
        	//1.【自画面共通】表示検索データ設定処理
        	CampNipoRefUtil.setView(getCampNipoRefSearchLogic()
        			, getCampNipoRefSesDto()
        			, getCampNipoRefReqDto()
        			, getCampNipoRefViewDto());
        }
        //３．Nullをリターンします。
        return null;
	}

	/** 
	 * 実行or再検索
	 * 
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.action.SibuListAction#search()
	 */
	public String search() {
		//１．DTO【自画面Request】.別ウィンドウオープン判断フラグがtrueの場合、下記の処理を行います。
        if(getCampNipoRefReqDto().isNewWindowFlg()) {
            //1.DTO【自画面Session】.ウィンドウID更新処理で新しいWindowIDを生成します。
            int windowId = getCampNipoRefSesDto().updateWindowid();
            //2.DTO【自画面Request】.ウィンドウIDへ処理1で生成した新しいWindowIDを設定します。
            getCampNipoRefReqDto().setWindowId(windowId);
            //3.DTO【自画面Request】.別ウィンドウオープン判断フラグにfalseを設定します。
            getCampNipoRefReqDto().setNewWindowFlg(false);
    	}
       	//２．【自画面共通】検索処理を行います。
		CampNipoRefUtil.search(getCampNipoRefSearchLogic(), getCampNipoRefSesDto(), getCampNipoRefReqDto());
		//３．DTO【自画面Request】.対象期間がNull又は空の場合は、
		//    DTO【自画面Session】.対象期間へ対象期間コード定数クラス.定数『期日指定日報』を設定します。
		if(CommonUtil.isNull(getCampNipoRefReqDto().getTaishoKikan())) {
			getCampNipoRefSesDto().setTaishoKikan(getCampNipoRefReqDto().getWindowId(), TaishoKikan.DAY1);
		}
		//４．Nullをリターンします。
		return null;
	}
	/**
	 * 会社変更処理
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.action.SibuListAction#changeCompany()
	 */
	public String changeCompany() {
        //１.ロジック【会社条件項目情報設定】を実行する。
		getCampNipoRefSetCompanyLogic().execute(getCampNipoRefReqDto());
        //２．Nullをリターンします。		
		return null;
	}

	/**
	 * キャンペーン変更処理
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.action.SibuListAction#changeCamp()
	 */
	public String changeCamp() {
		//１．ロジック【キャンペーン条件項目情報設定】を実行する。
		getCampNipoRefSetCampaignLogic().execute(getCampNipoRefReqDto());
	    //２．Nullをリターンします。
		return null;
	}
    /**
     * 支部リンク処理
     *
     */
    public String linkUp() {
    	return CampNipoRefUtil.VIEW_ID_MISE;
    }

	/**
	 * @return campNipoRefReqDto を戻します。
	 */
	public RequestNipoDto getCampNipoRefReqDto() {
		return campNipoRefReqDto;
	}

	/**
	 * @param campNipoRefReqDto 設定します campNipoRefReqDto。
	 */
	public void setCampNipoRefReqDto(RequestNipoDto campNipoRefReqDto) {
		this.campNipoRefReqDto = campNipoRefReqDto;
	}

	/**
	 * @return campNipoRefSesDto を戻します。
	 */
	public SessionNipoDto getCampNipoRefSesDto() {
		return campNipoRefSesDto;
	}

	/**
	 * @param campNipoRefSesDto 設定します campNipoRefSesDto。
	 */
	public void setCampNipoRefSesDto(SessionNipoDto campNipoRefSesDto) {
		this.campNipoRefSesDto = campNipoRefSesDto;
	}

	/**
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param pullDownMenuDto 設定します pullDownMenuDto。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

	/**
	 * @return campNipoRefConditionLogic を戻します。
	 */
	public ConditionLogic getCampNipoRefConditionLogic() {
		return campNipoRefConditionLogic;
	}

	/**
	 * @param campNipoRefConditionLogic 設定します campNipoRefConditionLogic。
	 */
	public void setCampNipoRefConditionLogic(
			ConditionLogic campNipoRefConditionLogic) {
		this.campNipoRefConditionLogic = campNipoRefConditionLogic;
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
	 * @return campNipoRefSearchLogic を戻します。
	 */
	public SearchLogic getCampNipoRefSearchLogic() {
		return campNipoRefSearchLogic;
	}

	/**
	 * @param campNipoRefSearchLogic 設定します campNipoRefSearchLogic。
	 */
	public void setCampNipoRefSearchLogic(SearchLogic campNipoRefSearchLogic) {
		this.campNipoRefSearchLogic = campNipoRefSearchLogic;
	}

	/**
	 * @return campNipoRefSetCampaignLogic を戻します。
	 */
	public SetCampaignLogic getCampNipoRefSetCampaignLogic() {
		return campNipoRefSetCampaignLogic;
	}

	/**
	 * @param campNipoRefSetCampaignLogic 設定する campNipoRefSetCampaignLogic。
	 */
	public void setCampNipoRefSetCampaignLogic(
			SetCampaignLogic campNipoRefSetCampaignLogic) {
		this.campNipoRefSetCampaignLogic = campNipoRefSetCampaignLogic;
	}

	/**
	 * @return campNipoRefSetCompanyLogic を戻します。
	 */
	public SetCompanyLogic getCampNipoRefSetCompanyLogic() {
		return campNipoRefSetCompanyLogic;
	}

	/**
	 * @param campNipoRefSetCompanyLogic 設定する campNipoRefSetCompanyLogic。
	 */
	public void setCampNipoRefSetCompanyLogic(
			SetCompanyLogic campNipoRefSetCompanyLogic) {
		this.campNipoRefSetCompanyLogic = campNipoRefSetCompanyLogic;
	}

	/**
	 * @return campNipoRefViewDto を戻します。
	 */
	public RequestNipoDto getCampNipoRefViewDto() {
		return campNipoRefViewDto;
	}

	/**
	 * @param campNipoRefViewDto 設定する campNipoRefViewDto。
	 */
	public void setCampNipoRefViewDto(RequestNipoDto campNipoRefViewDto) {
		this.campNipoRefViewDto = campNipoRefViewDto;
	}
	/**
	 * BIRD間の遷移時使用情報保持DTO取得処理
	 * 
	 * @return
	 */
    public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }

}
