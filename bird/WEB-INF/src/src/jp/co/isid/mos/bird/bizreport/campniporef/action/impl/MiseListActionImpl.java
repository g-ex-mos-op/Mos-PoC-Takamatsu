/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.action.impl;

import jp.co.isid.mos.bird.bizreport.campniporef.action.MiseListAction;
import jp.co.isid.mos.bird.bizreport.campniporef.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.logic.SetCampaignLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.logic.SetCompanyLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.util.CampNipoRefUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.code.MenuTotaledKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 店舗一覧用アクション
 * 
 * 2008/10/15 xkinu タブ構成(対象日・期間合計タブ)の廃止
 * @author xkinu
 *
 */
public class MiseListActionImpl implements MiseListAction {
	private static final String ACTION_ID = CampNipoRefUtil.SCREEN_ID+"A1";
    /* アクションID：初期処理(初期画面用) */
    public static final String initialize_ACTION_ID = ACTION_ID+"1";
    /* アクションID：対象会社変更処理 */
    public static final String changeCompany_ACTION_ID = ACTION_ID+"2";
    /* アクションID：対象キャンペーン変更処理 */
    public static final String changeCamp_ACTION_ID = ACTION_ID+"3";
    /* アクションID：検索処理 */
    public static final String execute_ACTION_ID = ACTION_ID+"4";
    /* アクションID：支部リンク処理 */
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
    /** DTO【自画面View用】*/
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
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.action.MiseListAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行います。
        if(getPullDownMenuDto().isClearFlg()){
            //1．DTO【プルダウンメニュー】.クリアフラグをfalseに設定します。
            getPullDownMenuDto().setClearFlg(false);
            //2.DTO【自画面リクエスト用】.画面ViewIDへ店舗一覧画面VIEWIDを設定します。
        	getCampNipoRefReqDto().setViewId(CampNipoRefUtil.VIEW_ID_MISE);
            //3．【自画面共通】初期化処理を行います。
            CampNipoRefUtil.initialize(getCampNipoRefConditionLogic()
            		, getCampNipoRefSesDto(), getCampNipoRefReqDto()
            		, getBirdDateInfo(), getBirdUserInfo()
            		);
        }
        else if((getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
        	//1.BIRD内遷移フラグをリセット
        	getCommonCodeDto().clear();
            //2.DTO【自画面リクエスト用】.画面ViewIDへ店舗一覧画面VIEWIDを設定します。
        	getCampNipoRefReqDto().setViewId(CampNipoRefUtil.VIEW_ID_MISE);
            //3．【自画面共通】初期化処理
            CampNipoRefUtil.initialize(getCampNipoRefConditionLogic()
            		, getCampNipoRefSesDto(), getCampNipoRefReqDto()
            		, getBirdDateInfo(), getBirdUserInfo()
            		);
        }
        //２．直前の画面が支部一覧画面の場合、下記の処理を行います。
        else if(CampNipoRefUtil.VIEW_ID_SIBU.equals(getCampNipoRefReqDto().getViewId())) {
            //1．DTO【自画面Session】.ウィンドウID更新処理で新しいWindowIDを生成します。
            int windowId = getCampNipoRefSesDto().updateWindowid();
            //2.DTO【自画面Request】.ウィンドウIDへ処理1で生成した新しいWindowIDを設定します。
            getCampNipoRefViewDto().setWindowId(windowId);
            //3.DTO【自画面Request】.別ウィンドウオープン判断フラグにfalseを設定します。
    		getCampNipoRefReqDto().setNewWindowFlg(false);
            //4.DTO【自画面リクエスト用】.画面ViewIDへ店舗一覧画面VIEWIDを設定します。
            getCampNipoRefViewDto().setViewId(CampNipoRefUtil.VIEW_ID_MISE);
        	//5.DTO【自画面View用】.対象条件へ”支部”を設定する。
        	getCampNipoRefViewDto().setTaishoJoken(getCampNipoRefViewDto().getShukeiKbn());
    		//6.遷移元一覧画面条件項目をDTO【自画面リクエスト用】へ全て置き換えます。
    		setCampNipoRefReqDto(getCampNipoRefViewDto());
           	//7.【自画面共通】検索処理を行います。
    		CampNipoRefUtil.search(getCampNipoRefSearchLogic(), getCampNipoRefSesDto(), getCampNipoRefReqDto());
			//8.DTO【自画面照会情報保持】へDTO【自画面セッション用】.検索済み条件値格納リクエストDTOを設定します。
    		getCampNipoRefSesDto().getSearchedDataDto(windowId, getCampNipoRefViewDto());
        	
        }
        //３．上記の処理以外の場合、下記の処理を行います。
        else {
        	//1.【自画面共通】表示検索データ設定処理を行います。
        	CampNipoRefUtil.setView(getCampNipoRefSearchLogic()
        			, getCampNipoRefSesDto()
        			, getCampNipoRefReqDto()
        			, getCampNipoRefViewDto());
        }
        //４．Nullをリターンします。
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
       	//２．【自画面共通】検索処理
		CampNipoRefUtil.search(getCampNipoRefSearchLogic(), getCampNipoRefSesDto(), getCampNipoRefReqDto());
		//３．DTO【自画面Request】.対象期間がNull又は空の場合は、
		//    DTO【自画面Session】.対象期間へ対象期間コード定数クラス.定数『期日指定日報』を設定します。
		if(CommonUtil.isNull(getCampNipoRefReqDto().getTaishoKikan())) {
			getCampNipoRefSesDto().setTaishoKikan(getCampNipoRefReqDto().getWindowId(), TaishoKikan.DAY1);
		}
		/*
		 * ４．店舗一覧画面IDをリターンします。
		 * （オーナ又は店舗ユーザーの場合
		 *  はじめの検索処理は支部一覧からこのアクションが呼ばれる為
		 *  明示的に店舗一覧画面IDをリターンするようにしている。）
		 */
		return CampNipoRefUtil.VIEW_ID_MISE;
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
     * 対象店舗の単品一覧への遷移処理
     *
     */
    public String linkUp() {
        //１．DTO【自画面Session】.ウィンドウID更新処理で新しいWindowIDを生成します。
        int windowId = getCampNipoRefSesDto().updateWindowid();
        //２．DTO【自画面Request】.ウィンドウIDへ処理1で生成した新しいWindowIDを設定します。
        getCampNipoRefViewDto().setWindowId(windowId);
        //３．DTO【自画面Request】.別ウィンドウオープン判断フラグにfalseを設定します。
		getCampNipoRefReqDto().setNewWindowFlg(false);
        //４．DTO【自画面リクエスト用】.画面ViewIDへ店舗一覧画面VIEWIDを設定します。
        getCampNipoRefViewDto().setViewId(CampNipoRefUtil.VIEW_ID_MISE);
    	//５．DTO【自画面View用】.メニュー集計区分へ”単品(集約)”を設定する。
    	getCampNipoRefViewDto().setMenuTotaledKbn(MenuTotaledKbn.CODE_TANPIN);
		//６．遷移元一覧画面条件項目をDTO【自画面リクエスト用】へ全て置き換えます。
		setCampNipoRefReqDto(getCampNipoRefViewDto());
       	//７．【自画面共通】検索処理を行います。
		CampNipoRefUtil.search(getCampNipoRefSearchLogic(), getCampNipoRefSesDto(), getCampNipoRefReqDto());
        //８．Nullをリターンします。		
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
