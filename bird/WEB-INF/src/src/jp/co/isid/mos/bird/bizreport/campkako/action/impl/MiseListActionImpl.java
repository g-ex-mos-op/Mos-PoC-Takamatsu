/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campkako.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.campkako.action.MiseListAction;
import jp.co.isid.mos.bird.bizreport.campkako.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.campkako.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.campkako.logic.SetCampaignLogic;
import jp.co.isid.mos.bird.bizreport.campkako.logic.SetCompanyLogic;
import jp.co.isid.mos.bird.bizreport.campkako.util.CampKakoUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.code.MenuTotaledKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * 支部一覧用アクション
 * 
 * @author xnkusama
 *
 */
public class MiseListActionImpl implements MiseListAction {
    /* アクションID：初期処理(初期画面用) */
    public static final String initialize_ACTION_ID = "BBR014A11";
    /* アクションID：対象キャンペーン変更処理 */
    public static final String changeCamp_ACTION_ID = "BBR014A12";
    /* アクションID：対象会社変更処理 */
    public static final String changeCompany_ACTION_ID = "BBR014A13";
    /* アクションID：年度変更処理 */
    public static final String changeNendo_ACTION_ID = "BBR014A14";
    /* アクションID：タブ切替処理 */
    public static final String changeTab_ACTION_ID = "BBR014A15";
    /* アクションID：単品リンク処理 */
    public static final String linkUp_ACTION_ID = "BBR014A16";
    /* アクションID：検索処理 */
    public static final String search_ACTION_ID = "BBR014A17";

    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** DTO【自画面リクエスト用】*/
    private RequestNipoDto campKakoRequestDto;
    /** DTO【自画面セッション用】*/
    private SessionNipoDto campKakoSessionDto;
    /** DTO【自画面照会情報保持】*/
    private RequestNipoDto campKakoViewRequestDto;
    /** LOGIC【条件項目取得】*/
    private ConditionLogic campKakoConditionLogic;
    /** LOGIC【会社条件項目情報設定】*/
    private SetCompanyLogic campKakoCompanyLogic;
    /** LOGIC【キャンペーン条件項目情報設定】*/
    private SetCampaignLogic campKakoSetCampaignLogic;
    /** LOGIC【検索結果取得】*/
    private SearchLogic campKakoSearchLogic;
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
        	getCampKakoRequestDto().setViewId(CampKakoUtil.VIEW_ID_MISE);
            //3．【自画面共通】初期化処理
            CampKakoUtil.initialize(getCampKakoSessionDto(), getCampKakoRequestDto()
            		, getBirdDateInfo(), getBirdUserInfo()
            		, getCampKakoConditionLogic());
        }
        //２．直前の画面が支部一覧画面の場合、下記の処理を行います。
        else if(CampKakoUtil.VIEW_ID_SIBU.equals(getCampKakoRequestDto().getViewId())) {
            //1．DTO【自画面Session】.ウィンドウID更新処理で新しいWindowIDを生成します。
            int windowId = getCampKakoSessionDto().updateWindowid();
            //2.DTO【自画面Request】.ウィンドウIDへ処理1で生成した新しいWindowIDを設定します。
            getCampKakoViewRequestDto().setWindowId(windowId);
            //3.DTO【自画面Request】.別ウィンドウオープン判断フラグにfalseを設定します。
            getCampKakoRequestDto().setNewWindowFlg(false);
            //4.DTO【自画面リクエスト用】.画面ViewIDへ店舗一覧画面VIEWIDを設定します。
            getCampKakoViewRequestDto().setViewId(CampKakoUtil.VIEW_ID_MISE);
            //5.DTO【自画面View用】.対象条件へ”支部”を設定する。
            getCampKakoViewRequestDto().setTaishoJoken(getCampKakoViewRequestDto().getShukeiKbn());
            //6.遷移元一覧画面条件項目をDTO【自画面リクエスト用】へ全て置き換えます。
            setCampKakoRequestDto(getCampKakoViewRequestDto());
            //6-1.DTO【自画面Request】へプルダウンリストを設定します。
            getCampKakoViewRequestDto().setPullDownData(getCampKakoSessionDto());

            //7.【自画面共通】検索処理を行います。
            CampKakoUtil.search(getCampKakoSearchLogic(), getCampKakoSessionDto(), getCampKakoRequestDto());
            //8.DTO【自画面照会情報保持】へDTO【自画面セッション用】.検索済み条件値格納リクエストDTOを設定します。
            getCampKakoSessionDto().getSearchedDataDto(windowId, getCampKakoViewRequestDto());

        }
        //３．上記の処理以外の場合、下記の処理を行います。
        else {
        	//1.【自画面共通】表示検索データ設定処理
        	CampKakoUtil.setView(getCampKakoSearchLogic()
        			, getCampKakoSessionDto()
        			, getCampKakoRequestDto()
                    , getCampKakoViewRequestDto());
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
        if(getCampKakoRequestDto().isNewWindowFlg()) {
            //1.DTO【自画面Session】.ウィンドウID更新処理で新しいWindowIDを生成します。
            int windowId = getCampKakoSessionDto().updateWindowid();
            //2.DTO【自画面Request】.ウィンドウIDへ処理1で生成した新しいWindowIDを設定します。
            getCampKakoRequestDto().setWindowId(windowId);
            //3.DTO【自画面Request】.別ウィンドウオープン判断フラグにfalseを設定します。
            getCampKakoRequestDto().setNewWindowFlg(false);
        }
        //２．【自画面共通】検索処理
        CampKakoUtil.search(getCampKakoSearchLogic(), getCampKakoSessionDto(), getCampKakoRequestDto());
        /*
         * ４．店舗一覧画面IDをリターンします。
         * （オーナ又は店舗ユーザーの場合
         *  はじめの検索処理は支部一覧からこのアクションが呼ばれる為
         *  明示的に店舗一覧画面IDをリターンするようにしている。）
         */
        return CampKakoUtil.VIEW_ID_MISE;
	}
	/**
	 * 会社変更処理
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.action.SibuListAction#changeCompany()
	 */
	public String changeCompany() {
        //１.ロジック【会社条件項目情報設定】を実行する。
		getCampKakoCompanyLogic().execute(getCampKakoRequestDto());
        //２．Nullをリターンします。		
		return null;
	}
    /**
     * 対象店舗の単品一覧への遷移処理
     *
     */
    public String linkUp() {
        //１．DTO【自画面Session】.ウィンドウID更新処理で新しいWindowIDを生成します。
        int windowId = getCampKakoSessionDto().updateWindowid();
        //２．DTO【自画面Request】.ウィンドウIDへ処理1で生成した新しいWindowIDを設定します。
        getCampKakoViewRequestDto().setWindowId(windowId);
        //３．DTO【自画面Request】.別ウィンドウオープン判断フラグにfalseを設定します。
        getCampKakoRequestDto().setNewWindowFlg(false);
        //４．DTO【自画面リクエスト用】.画面ViewIDへ店舗一覧画面VIEWIDを設定します。
        getCampKakoViewRequestDto().setViewId(CampKakoUtil.VIEW_ID_MISE);
        //５．DTO【自画面View用】.メニュー集計区分へ”単品(集約)”を設定する。
        getCampKakoViewRequestDto().setMenuTotaledKbn(MenuTotaledKbn.CODE_TANPIN);
        //６．遷移元一覧画面条件項目をDTO【自画面リクエスト用】へ全て置き換えます。
        setCampKakoRequestDto(getCampKakoViewRequestDto());
        //７．【自画面共通】検索処理を行います。
        CampKakoUtil.search(getCampKakoSearchLogic(), getCampKakoSessionDto(), getCampKakoRequestDto());
        //８．Nullをリターンします。       
        return null;
    
    }

	/**
	 * キャンペーン変更処理
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.action.SibuListAction#changeCamp()
	 */
	public String changeCamp() {
		//１．ロジック【キャンペーン条件項目情報設定】を実行する。
		getCampKakoSetCampaignLogic().execute(getCampKakoRequestDto());
	    //２．Nullをリターンします。		
		return null;
	}
    /**
     * タブ変更処理
     *
     */
    public String changeTab() {
        getPullDownMenuDto().setClearFlg(true);
        return CampKakoUtil.VIEW_ID_SUII;
    }
    
    /**
     * 年度変更処理
     */
    public String changeNendo() {
        //１．キャンペーンリスト
        List listCamp = getCampKakoSessionDto().getListCamp(getCampKakoRequestDto().getCompanyCd(), getCampKakoRequestDto().getNendo());
        getCampKakoRequestDto().setListsCamp(listCamp);
        
        return null;
    }
    
    /**
	 * @return campNipoRefReqDto を戻します。
	 */
	public RequestNipoDto getCampKakoRequestDto() {
		return campKakoRequestDto;
	}

	/**
	 * @param campNipoRefReqDto 設定します campNipoRefReqDto。
	 */
	public void setCampKakoRequestDto(RequestNipoDto campNipoRefReqDto) {
		this.campKakoRequestDto = campNipoRefReqDto;
	}

	/**
	 * @return campNipoRefSesDto を戻します。
	 */
	public SessionNipoDto getCampKakoSessionDto() {
		return campKakoSessionDto;
	}

	/**
	 * @param campNipoRefSesDto 設定します campNipoRefSesDto。
	 */
	public void setCampKakoSessionDto(SessionNipoDto campNipoRefSesDto) {
		this.campKakoSessionDto = campNipoRefSesDto;
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
	public ConditionLogic getCampKakoConditionLogic() {
		return campKakoConditionLogic;
	}

	/**
	 * @param campNipoRefConditionLogic 設定します campNipoRefConditionLogic。
	 */
	public void setCampKakoConditionLogic(
			ConditionLogic campNipoRefConditionLogic) {
		this.campKakoConditionLogic = campNipoRefConditionLogic;
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
	public SearchLogic getCampKakoSearchLogic() {
		return campKakoSearchLogic;
	}

	/**
	 * @param campNipoRefSearchLogic 設定します campNipoRefSearchLogic。
	 */
	public void setCampKakoSearchLogic(SearchLogic campNipoRefSearchLogic) {
		this.campKakoSearchLogic = campNipoRefSearchLogic;
	}

	/**
	 * @return campNipoRefSetCampaignLogic を戻します。
	 */
	public SetCampaignLogic getCampKakoSetCampaignLogic() {
		return campKakoSetCampaignLogic;
	}

	/**
	 * @param campNipoRefSetCampaignLogic 設定する campNipoRefSetCampaignLogic。
	 */
	public void setCampKakoSetCampaignLogic(
			SetCampaignLogic campNipoRefSetCampaignLogic) {
		this.campKakoSetCampaignLogic = campNipoRefSetCampaignLogic;
	}

	/**
	 * @return campNipoRefSetCompanyLogic を戻します。
	 */
	public SetCompanyLogic getCampKakoCompanyLogic() {
		return campKakoCompanyLogic;
	}

	/**
	 * @param campNipoRefSetCompanyLogic 設定する campNipoRefSetCompanyLogic。
	 */
	public void setCampKakoCompanyLogic(
			SetCompanyLogic campNipoRefSetCompanyLogic) {
		this.campKakoCompanyLogic = campNipoRefSetCompanyLogic;
	}

    public RequestNipoDto getCampKakoViewRequestDto() {
        return campKakoViewRequestDto;
    }

    public void setCampKakoViewRequestDto(RequestNipoDto campKakoViewRequestDto) {
        this.campKakoViewRequestDto = campKakoViewRequestDto;
    }

}
